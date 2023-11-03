package com.zb.loanproject.type;

import static com.zb.loanproject.type.ErrorCode.UNMATCHED_ORGANIZATION_CODE;
import static com.zb.loanproject.type.ErrorCode.UNMATCHED_ORGANIZATION_NAME;

import com.zb.loanproject.exception.GlobalException;
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
                     .orElseThrow(() -> new GlobalException(UNMATCHED_ORGANIZATION_CODE));
    }

    public static OrganizationEnum ofName(String name) {
        return Arrays.stream(OrganizationEnum.values())
                     .filter(p -> p.name.equals(name))
                     .findAny()
                     .orElseThrow(() -> new GlobalException(UNMATCHED_ORGANIZATION_NAME));
    }

    public static String codeOfName(String name) {
        return Arrays.stream(OrganizationEnum.values())
                     .filter(p -> p.name.equals(name))
                     .findAny()
                     .map(OrganizationEnum::getCode)
                     .orElseThrow(() -> new GlobalException(UNMATCHED_ORGANIZATION_NAME));
    }
}
