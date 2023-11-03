package com.zb.loanproject.dto.user;

import com.zb.loanproject.dto.ApiResponse;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class UserInfo {

    @Data
    public static class Request {

        private Long userIncomeAmount;
        private String userName;
        private String userRegistrationNumber;
    }

    @Getter
    @Setter
    @SuperBuilder
    public static class Response extends ApiResponse {

        private Map<String, String> data;
    }
}
