package com.zb.loanproject.domain.converter;

import com.zb.loanproject.type.ProductInfo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProductConverter implements AttributeConverter<ProductInfo, String> {

    @Override
    public String convertToDatabaseColumn(ProductInfo attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public ProductInfo convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return ProductInfo.ofCode(dbData);
    }
}
