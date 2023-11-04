package com.loan.core.entity.converter;

import com.loan.core.type.ProductEnum;
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
