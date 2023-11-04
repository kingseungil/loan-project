package com.loan.core.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.loan.core.dto.ApiDefaultResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class ProductInfo {

    @Data
    public static class ProductRequest {

        @Schema(example = "00001")
        private String organizationCode;
        @Schema(example = "001")
        private String productCode;
        @Schema(example = "9.9")
        private double productMaximumInterest;
        @Schema(example = "1.1")
        private double productMinimumInterest;
        @Schema(example = "kakaoBank")
        private String productName;
    }

    @Getter
    @Setter
    @SuperBuilder
    @JsonInclude(Include.NON_NULL)
    public static class ProductResponse extends ApiDefaultResponse {

        private List<ProductDto> data;
    }
}

