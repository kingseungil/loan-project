package com.zb.loanproject.type;

import static com.zb.loanproject.type.ErrorCode.UNMATCHED_PRODUCT_CODE;
import static com.zb.loanproject.type.ErrorCode.UNMATCHED_PRODUCT_NAME;

import com.zb.loanproject.exception.GlobalException;
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
                     .orElseThrow(() -> new GlobalException(UNMATCHED_PRODUCT_CODE));
    }

    public static String codeOfName(String name) {
        return Arrays.stream(ProductEnum.values())
                     .filter(p -> p.name.equals(name))
                     .findAny()
                     .map(ProductEnum::getCode)
                     .orElseThrow(() -> new GlobalException(UNMATCHED_PRODUCT_NAME));
    }
}
