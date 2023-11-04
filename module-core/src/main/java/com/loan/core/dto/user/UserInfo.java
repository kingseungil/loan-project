package com.loan.core.dto.user;

import com.loan.core.dto.ApiDefaultResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class UserInfo {

    @Data
    public static class UserRequest {

        @Schema(example = "10000")
        private Long userIncomeAmount;
        @Schema(example = "이름")
        private String userName;
        @Schema(example = "999999-9999999")
        private String userRegistrationNumber;
    }

    @Getter
    @Setter
    @SuperBuilder
    public static class UserResponse extends ApiDefaultResponse {

        private Map<String, String> data;
    }
}
