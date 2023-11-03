package com.zb.loanproject.type;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum ProductEnum {
    SERVICE1("Service1", "001"),
    SERVICE2("Service2", "002"),
    SERVICE3("Service3", "003");

    private final String name;
    private final String code;

    ProductEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static ProductEnum ofCode(String code) {
        return Arrays.stream(ProductEnum.values())
                     .filter(p -> p.code.equals(code))
                     .findAny()
                     // TODO : custom exception 적용하기
                     .orElseThrow(IllegalArgumentException::new);
    }

    public static String codeOfName(String name) {
        return Arrays.stream(ProductEnum.values())
                     .filter(p -> p.name.equals(name))
                     .findAny()
                     .map(ProductEnum::getCode)
                     // TODO : custom exception 적용하기
                     .orElseThrow(IllegalArgumentException::new);
    }
}
