package com.zb.loanproject.service;

import com.zb.loanproject.domain.Organization;
import com.zb.loanproject.domain.Product;
import com.zb.loanproject.dto.product.ProductDto;
import com.zb.loanproject.dto.product.ProductInfo.ProductRequest;
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
            // TODO : custom exception 적용하기
            throw new IllegalArgumentException("Invalid product name");
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
                                                          // TODO : custom exception 적용하기
                                                          .orElseThrow(IllegalArgumentException::new);
        List<Product> products = productRepository.findByOrganization(organization)
                                                  // TODO : custom exception 적용하기
                                                  .orElseThrow(IllegalArgumentException::new);

        return products.stream()
                       .map(ProductDto::convertToDto)
                       .toList();
    }
}
