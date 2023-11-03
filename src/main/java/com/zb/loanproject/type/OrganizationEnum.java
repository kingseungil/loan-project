package com.zb.loanproject.type;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum OrganizationEnum {
    KAKAO("kakaoBank", "00001"),
    TOSS("tossBank", "00002");

    private final String name;
    private final String code;

    OrganizationEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static OrganizationEnum ofCode(String code) {
        return Arrays.stream(OrganizationEnum.values())
                     .filter(p -> p.code.equals(code))
                     .findAny()
                     // TODO : custom exception 적용하기
                     .orElseThrow(IllegalArgumentException::new);
    }

    public static OrganizationEnum ofName(String name) {
        return Arrays.stream(OrganizationEnum.values())
                     .filter(p -> p.name.equals(name))
                     .findAny()
                     // TODO : custom exception 적용하기
                     .orElseThrow(IllegalArgumentException::new);
    }

    public static String codeOfName(String name) {
        return Arrays.stream(OrganizationEnum.values())
                     .filter(p -> p.name.equals(name))
                     .findAny()
                     .map(OrganizationEnum::getCode)
                     // TODO : custom exception 적용하기
                     .orElseThrow(IllegalArgumentException::new);
    }
}
