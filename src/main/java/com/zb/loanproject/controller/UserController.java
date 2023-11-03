package com.zb.loanproject.controller;

import com.zb.loanproject.dto.ApiDefaultResponse;
import com.zb.loanproject.dto.user.PrivateUserInfoDto;
import com.zb.loanproject.dto.user.UserInfo.UserRequest;
import com.zb.loanproject.dto.user.UserInfo.UserResponse;
import com.zb.loanproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fintech/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 정보 수신 API", description = "userKey를 받는 API")
    @PostMapping("/information")
    public ResponseEntity<UserResponse> getUserInformation(
      @RequestBody UserRequest userRequest
    ) {
        String userKey = userService.getUserInformation(userRequest);
        Map<String, String> responseData = Map.of("userKey", userKey);

        return ResponseEntity.status(201).body(UserResponse.builder()
                                                           .data(responseData)
                                                           .responseCode("201")
                                                           .responseMessage("성공")
                                                           .build());
    }

    @Operation(summary = "유저 정보 조회 API", description = "userKey로 유저 정보 조회")
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200",
        description = "성공"
      ),
      @ApiResponse(
        responseCode = "400",
        description = "잘못된 요청"
      ),
    })
    @GetMapping("/private-info/{userKey}")
    public ResponseEntity<ApiDefaultResponse> getUserPrivateInformation(
      @Parameter(description = "유저 키", example = "userKey", required = true)
      @PathVariable String userKey) {
        PrivateUserInfoDto privateUserInfo = userService.getPrivateInformation(userKey);
        Map<String, String> responseData = Map.of("userKey", privateUserInfo.getUserKey(),
          "userRegistrationNumber", privateUserInfo.getUserRegistrationNumber());

        return ResponseEntity.ok(UserResponse.builder()
                                             .data(responseData)
                                             .responseCode("200")
                                             .responseMessage("성공")
                                             .build());
    }
}
