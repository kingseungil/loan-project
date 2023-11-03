package com.zb.loanproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrivateUserInfoDto {

    private String userKey;
    private String userRegistrationNumber;
}
