package com.zb.loanproject.domain.converter;

import com.zb.loanproject.type.ProductEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ProductConverter implements AttributeConverter<ProductEnum, String> {

    @Override
    public String convertToDatabaseColumn(ProductEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public ProductEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return ProductEnum.ofCode(dbData);
    }
}
