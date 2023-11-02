package com.zb.loanproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PrivateUserInfo extends UserResponseData {

    private String userRegistrationNumber;

    public PrivateUserInfo(String userKey, String userRegistrationNumber) {
        super.setUserKey(userKey);
        this.userRegistrationNumber = userRegistrationNumber;
    }
}
