package com.zb.loanproject.type;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum ProductInfo {
    SERVICE1("Service 1", "001"),
    SERVICE2("Service 2", "002"),
    SERVICE3("Service 3", "003");

    private final String name;
    private final String code;

    ProductInfo(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static ProductInfo ofCode(String code) {
        return Arrays.stream(ProductInfo.values())
                     .filter(p -> p.code.equals(code))
                     .findAny()
                     // TODO : custom exception 적용하기
                     .orElseThrow(IllegalArgumentException::new);
    }

    public static String codeOfName(String name) {
        return Arrays.stream(ProductInfo.values())
                     .filter(p -> p.name.equals(name))
                     .findAny()
                     .map(ProductInfo::getCode)
                     // TODO : custom exception 적용하기
                     .orElseThrow(IllegalArgumentException::new);
    }
}
