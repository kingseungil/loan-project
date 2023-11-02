package com.zb.loanproject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserInfo {

    @Data
    public static class Request {

        private Long userIncomeAmount;
        private String userName;
        private String userRegistrationNumber;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UserInfoResponse extends UserResponseData {

        public UserInfoResponse(String userKey) {
            super.setUserKey(userKey);
        }
    }
}
