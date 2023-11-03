package com.zb.loanproject.controller;

import com.zb.loanproject.dto.ApiResponse;
import com.zb.loanproject.dto.product.ProductDto;
import com.zb.loanproject.dto.product.ProductInfo;
import com.zb.loanproject.service.ProductService;
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
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{organizationName}")
    public ResponseEntity<ApiResponse> getProductInfo(
      @PathVariable String organizationName
    ) {
        List<ProductDto> productInfo = productService.getProductInfo(organizationName);
        return ResponseEntity.ok(ProductInfo.Response.builder()
                                                     .data(productInfo)
                                                     .responseCode("200")
                                                     .responseMessage("성공")
                                                     .build());
    }

    @PostMapping("/information")
    public ResponseEntity<ApiResponse> getProductInformation(
      @RequestBody ProductInfo.Request request
    ) {
        productService.getProductInformation(request);
        return ResponseEntity.status(201).body(ProductInfo.Response.builder()
                                                                   .responseCode("200")
                                                                   .responseMessage("성공")
                                                                   .build());
    }
}
