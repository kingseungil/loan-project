package com.loan.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ApiDefaultResponse {

    private String responseCode;
    private String responseMessage;

}
