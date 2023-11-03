package com.zb.loanproject.service;

import static com.zb.loanproject.type.ErrorCode.ORGANIZATION_NOT_FOUND;
import static com.zb.loanproject.type.ErrorCode.PRODUCT_NOT_FOUND;
import static com.zb.loanproject.type.ErrorCode.UNMATCHED_PRODUCT_NAME;

import com.zb.loanproject.domain.Organization;
import com.zb.loanproject.domain.Product;
import com.zb.loanproject.dto.product.ProductDto;
import com.zb.loanproject.dto.product.ProductInfo.ProductRequest;
import com.zb.loanproject.exception.GlobalException;
import com.zb.loanproject.repository.OrganizatonRespository;
import com.zb.loanproject.repository.ProductRepository;
import com.zb.loanproject.type.OrganizationEnum;
import com.zb.loanproject.type.ProductEnum;
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
