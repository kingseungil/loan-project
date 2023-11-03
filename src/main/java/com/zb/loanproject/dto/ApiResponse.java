package com.zb.loanproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ApiResponse {

    private String responseCode;
    private String responseMessage;

}
