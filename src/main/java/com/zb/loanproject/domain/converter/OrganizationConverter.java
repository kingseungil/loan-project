package com.zb.loanproject.domain.converter;

import com.zb.loanproject.type.OrganizationEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class OrganizationConverter implements AttributeConverter<OrganizationEnum, String> {

    @Override
    public String convertToDatabaseColumn(OrganizationEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public OrganizationEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return OrganizationEnum.ofCode(dbData);
    }
}
