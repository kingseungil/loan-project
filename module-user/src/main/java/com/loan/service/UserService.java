package com.loan.service;

import static com.loan.core.type.ErrorCode.ORGANIZATION_NOT_FOUND;

import com.loan.core.dto.user.PrivateUserInfoDto;
import com.loan.core.dto.user.UserInfo.UserRequest;
import com.loan.core.entity.User;
import com.loan.core.exception.GlobalException;
import com.loan.core.repository.UserRepository;
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
                                        .orElseThrow(() -> new GlobalException(ORGANIZATION_NOT_FOUND));

        return new PrivateUserInfoDto(userEntity.getUserKey(), userEntity.getRegNumber());
    }
}
