package com.zb.loanproject.service;

import com.zb.loanproject.domain.Organization;
import com.zb.loanproject.domain.Product;
import com.zb.loanproject.dto.product.ProductDto;
import com.zb.loanproject.dto.product.ProductInfo.Request;
import com.zb.loanproject.repository.OrganizatonRespository;
import com.zb.loanproject.repository.ProductRepository;
import com.zb.loanproject.type.OrganizationEnum;
import com.zb.loanproject.type.ProductEnum;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrganizatonRespository organizatonRespository;

    public void getProductInformation(Request request) {
        String organizationCode = request.getOrganizationCode();
        String productCode = request.getProductCode();
        double productMaximumInterest = request.getProductMaximumInterest();
        double productMinimumInterest = request.getProductMinimumInterest();
        String productName = request.getProductName();

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
