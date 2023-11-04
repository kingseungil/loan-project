package com.loan.core.dto.product;

import com.loan.core.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String organizationCode;
    private String productCode;
    private double productMaximumInterest;
    private double productMinimumInterest;
    private String productName;

    public static ProductDto convertToDto(Product product) {
        return builder()
          .organizationCode(product.getOrganization().getOrgEnum().getCode())
          .productCode(product.getProductEnum().getCode())
          .productMaximumInterest(product.getMaxInterest())
          .productMinimumInterest(product.getMinInterest())
          .productName(product.getProductEnum().getName())
          .build();
    }
}
