package com.zb.loanproject.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zb.loanproject.dto.ApiResponse;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class ProductInfo {

    @Data
    public static class Request {

        private String organizationCode;
        private String productCode;
        private double productMaximumInterest;
        private double productMinimumInterest;
        private String productName;
    }

    @Getter
    @Setter
    @SuperBuilder
    @JsonInclude(Include.NON_NULL)
    public static class Response extends ApiResponse {

        private List<ProductDto> data;
    }
}

