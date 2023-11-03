package com.zb.loanproject.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_REQUEST("잘못된 요청입니다."),
    USER_NOT_FOUND("사용자가 존재하지 않습니다."),
    ORGANIZATION_NOT_FOUND("기관이 존재하지 않습니다."),
    PRODUCT_NOT_FOUND("상품이 존재하지 않습니다."),
    UNMATCHED_PRODUCT_NAME("상품이 존재하지 않거나, 이름이 일치하지 않습니다."),
    UNMATCHED_PRODUCT_CODE("상품이 존재하지 않거나, 코드가 일치하지 않습니다."),
    UNMATCHED_ORGANIZATION_CODE("기관이 존재하지 않거나, 코드가 일치하지 않습니다."),
    UNMATCHED_ORGANIZATION_NAME("기관이 존재하지 않거나, 이름이 일치하지 않습니다."),
    INTERNAL_SERVER_ERROR("서버 내부 에러가 발생했습니다.");

    private final String description;
}
