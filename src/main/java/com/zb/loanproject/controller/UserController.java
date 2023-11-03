package com.zb.loanproject.controller;

import com.zb.loanproject.dto.ApiResponse;
import com.zb.loanproject.dto.user.PrivateUserInfoDto;
import com.zb.loanproject.dto.user.UserInfo;
import com.zb.loanproject.dto.user.UserInfo.Request;
import com.zb.loanproject.service.UserService;
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

    @PostMapping("/information")
    public ResponseEntity<ApiResponse> getUserInformation(
      @RequestBody Request request
    ) {
        String userKey = userService.getUserInformation(request);
        Map<String, String> responseData = Map.of("userKey", userKey);

        return ResponseEntity.status(201).body(UserInfo.Response.builder()
                                                                .data(responseData)
                                                                .responseCode("201")
                                                                .responseMessage("성공")
                                                                .build());
    }

    @GetMapping("/private-info/{userKey}")
    public ResponseEntity<ApiResponse> getUserPrivateInformation(@PathVariable String userKey) {
        PrivateUserInfoDto privateUserInfo = userService.getPrivateInformation(userKey);
        Map<String, String> responseData = Map.of("userKey", privateUserInfo.getUserKey(),
          "userRegistrationNumber", privateUserInfo.getUserRegistrationNumber());

        return ResponseEntity.ok(UserInfo.Response.builder()
                                                  .data(responseData)
                                                  .responseCode("200")
                                                  .responseMessage("성공")
                                                  .build());
    }
}
