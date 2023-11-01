package com.zb.loanproject.type;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum OrganizationInfo {
    ORG1("Organization 1", "00001"),
    ORG2("Organization 2", "00002"),
    ORG3("Organization 3", "00003");

    private final String name;
    private final String code;

    OrganizationInfo(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static OrganizationInfo ofCode(String code) {
        return Arrays.stream(OrganizationInfo.values())
                     .filter(p -> p.code.equals(code))
                     .findAny()
                     // TODO : custom exception 적용하기
                     .orElseThrow(IllegalArgumentException::new);
    }

    public static String codeOfName(String name) {
        return Arrays.stream(OrganizationInfo.values())
                     .filter(p -> p.name.equals(name))
                     .findAny()
                     .map(OrganizationInfo::getCode)
                     // TODO : custom exception 적용하기
                     .orElseThrow(IllegalArgumentException::new);
    }
}
