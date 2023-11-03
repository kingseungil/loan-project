package com.zb.loanproject.service;

import com.zb.loanproject.domain.User;
import com.zb.loanproject.dto.user.PrivateUserInfoDto;
import com.zb.loanproject.dto.user.UserInfo.UserRequest;
import com.zb.loanproject.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String getUserInformation(UserRequest userRequest) {
        String userName = userRequest.getUserName();
        String userRegistrationNumber = userRequest.getUserRegistrationNumber();
        Long userIncomeAmount = userRequest.getUserIncomeAmount();

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
        return userKey;
    }

    public PrivateUserInfoDto getPrivateInformation(String userKey) {
        User userEntity = userRepository.findByUserKey(userKey)
                                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        return new PrivateUserInfoDto(userEntity.getUserKey(), userEntity.getRegNumber());
    }
}
