package com.zb.loanproject.service;

import com.zb.loanproject.domain.User;
import com.zb.loanproject.dto.PrivateUserInfo;
import com.zb.loanproject.dto.UserInfo.Request;
import com.zb.loanproject.dto.UserInfo.UserInfoResponse;
import com.zb.loanproject.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResponse getUserInformation(Request request) {
        String userName = request.getUserName();
        String userRegistrationNumber = request.getUserRegistrationNumber();
        Long userIncomeAmount = request.getUserIncomeAmount();

        // 유저 정보를 저장하고, 유저 키를 반환한다.
        User userEntity = User.builder()
                              .name(userName)
                              .incomeAmount(userIncomeAmount)
                              .regNumber(userRegistrationNumber)
                              .build();

        // 키 생성
        String userKey = UUID.randomUUID().toString();
        userEntity.setUserKey(userKey);

        userRepository.save(userEntity);
        return new UserInfoResponse(userKey);
    }

    public PrivateUserInfo getPrivateInformation(String userKey) {
        User userEntity = userRepository.findByUserKey(userKey)
                                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        return new PrivateUserInfo(userEntity.getUserKey(), userEntity.getRegNumber());
    }
}
