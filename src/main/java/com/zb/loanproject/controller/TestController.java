package com.zb.loanproject.controller;

import com.zb.loanproject.domain.User;
import com.zb.loanproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;

    @GetMapping("/test")
    public void testSave() {
        User user = User.builder()
                        .userKey("test")
                        .name("test")
                        .password("test")
                        .regNumber("test")
                        .incomeAmount(1L)
                        .build();

        userRepository.save(user);
    }

    @GetMapping("/test2")
    public User testGet() {

        return userRepository.findById(11L).get();
    }
}
