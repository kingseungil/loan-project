package com.loan.service;

import static com.loan.core.type.ErrorCode.ORGANIZATION_NOT_FOUND;
import static com.loan.core.type.ErrorCode.PRODUCT_NOT_FOUND;
import static com.loan.core.type.ErrorCode.UNMATCHED_PRODUCT_NAME;

import com.loan.core.dto.product.ProductDto;
import com.loan.core.dto.product.ProductInfo.ProductRequest;
import com.loan.core.entity.Organization;
import com.loan.core.entity.Product;
import com.loan.core.exception.GlobalException;
import com.loan.core.repository.OrganizatonRespository;
import com.loan.core.repository.ProductRepository;
import com.loan.core.type.OrganizationEnum;
import com.loan.core.type.ProductEnum;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrganizatonRespository organizatonRespository;

    @CacheEvict(value = "productInfo", allEntries = true)
    public void getProductInformation(ProductRequest productRequest) {
        String organizationCode = productRequest.getOrganizationCode();
        String productCode = productRequest.getProductCode();
        double productMaximumInterest = productRequest.getProductMaximumInterest();
        double productMinimumInterest = productRequest.getProductMinimumInterest();
        String productName = productRequest.getProductName();

        ProductEnum productInfo = ProductEnum.ofCode(productCode);
        OrganizationEnum organizationInfo = OrganizationEnum.ofCode(organizationCode);

        if (!productInfo.getName().equals(productName)) {
            throw new GlobalException(UNMATCHED_PRODUCT_NAME);
        }

        Optional<Organization> optionalOrganization = organizatonRespository.findByOrgEnum(organizationInfo);
        Organization savedOrganization = optionalOrganization.orElseGet(
          () -> organizatonRespository.save(Organization.builder()
                                                        .orgEnum(organizationInfo)
                                                        .build()));
        productRepository.save(Product.builder()
                                      .productEnum(productInfo)
                                      .organization(savedOrganization)
                                      .minInterest(productMinimumInterest)
                                      .maxInterest(productMaximumInterest)
                                      .build());

    }

    @Cacheable(key = "#organizationName", value = "productInfo")
    public List<ProductDto> getProductInfo(String organizationName) {
        Organization organization = organizatonRespository.findByOrgEnum(OrganizationEnum.ofName(organizationName))
                                                          .orElseThrow(
                                                            () -> new GlobalException(ORGANIZATION_NOT_FOUND));
        List<Product> products = productRepository.findByOrganization(organization)
                                                  .orElseThrow(
                                                    () -> new GlobalException(PRODUCT_NOT_FOUND));

        return products.stream()
                       .map(ProductDto::convertToDto)
                       .toList();
    }
}
