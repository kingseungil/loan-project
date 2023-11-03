package com.zb.loanproject.controller;

import com.zb.loanproject.dto.product.ProductDto;
import com.zb.loanproject.dto.product.ProductInfo.ProductRequest;
import com.zb.loanproject.dto.product.ProductInfo.ProductResponse;
import com.zb.loanproject.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/fintech/v1/product")
@Tag(name = "Product", description = "상품 API")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 정보 조회 API", description = "기관명으로 상품 정보 조회")
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200",
        description = "성공"
      ),
      @ApiResponse(
        responseCode = "400",
        description = "잘못된 요청"
      ),
    }
    )
    @GetMapping("/{organizationName}")
    public ResponseEntity<ProductResponse> getProductInfo(
      @Parameter(description = "기관명", example = "kakaoBank", required = true)
      @PathVariable String organizationName
    ) {
        List<ProductDto> productInfo = productService.getProductInfo(organizationName);
        return ResponseEntity.ok(ProductResponse.builder()
                                                .data(productInfo)
                                                .responseCode("200")
                                                .responseMessage("성공")
                                                .build());
    }

    @Operation(summary = "상품 정보 수신 API", description = "기관으로부터 상품 정보를 받는 API")
    @PostMapping("/information")
    public ResponseEntity<ProductResponse> getProductInformation(
      @RequestBody ProductRequest productRequest
    ) {
        productService.getProductInformation(productRequest);
        return ResponseEntity.status(201).body(ProductResponse.builder()
                                                              .responseCode("200")
                                                              .responseMessage("성공")
                                                              .build());
    }
}
