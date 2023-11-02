package com.zb.loanproject.controller;

import com.zb.loanproject.domain.User;
import com.zb.loanproject.repository.UserRepository;
import com.zb.loanproject.security.TokenProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @GetMapping("/test/1")
    public String test() {
        User user = User.builder()
                        .name("test")
                        .password("test")
                        .userKey("test")
                        .incomeAmount(1L)
                        .regNumber("test")
                        .roles(List.of("ROLE_USER", "ROLE_ADMIN"))
                        .build();
        User save = userRepository.save(user);
        log.info("save -> " + save);
        String token = tokenProvider.generateToken("test", List.of("ROLE_USER"));
        return token;

    }


}
