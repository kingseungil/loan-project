package com.zb.loanproject.controller;

import com.zb.loanproject.dto.PrivateUserInfo;
import com.zb.loanproject.dto.ResponseDTO;
import com.zb.loanproject.dto.UserInfo;
import com.zb.loanproject.dto.UserInfo.UserInfoResponse;
import com.zb.loanproject.service.UserService;
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
    public ResponseEntity<ResponseDTO> getUserInformation(
      @RequestBody UserInfo.Request request
    ) {
        UserInfoResponse userKey = userService.getUserInformation(request);

        return ResponseEntity.ok(ResponseDTO.builder()
                                            .data(userKey)
                                            .responseCode("201")
                                            .responseMessage("성공")
                                            .build());
    }

    @GetMapping("/private-info/{userKey}")
    public ResponseEntity<ResponseDTO> getUserPrivateInformation(@PathVariable String userKey) {
        PrivateUserInfo privateUserInfo = userService.getPrivateInformation(userKey);

        return ResponseEntity.ok(ResponseDTO.builder()
                                            .data(privateUserInfo)
                                            .responseCode("201")
                                            .responseMessage("성공")
                                            .build());
    }
}
