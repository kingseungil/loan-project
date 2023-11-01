package com.zb.loanproject.domain.converter;

import com.zb.loanproject.type.OrganizationInfo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class OrganizationConverter implements AttributeConverter<OrganizationInfo, String> {

    @Override
    public String convertToDatabaseColumn(OrganizationInfo attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public OrganizationInfo convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return OrganizationInfo.ofCode(dbData);
    }
}
