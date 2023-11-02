package com.zb.loanproject.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDTO {

    private UserResponseData data;
    private String responseCode;
    private String responseMessage;
}
