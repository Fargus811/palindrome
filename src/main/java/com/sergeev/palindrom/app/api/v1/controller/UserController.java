package com.sergeev.palindrom.app.api.v1.controller;

import com.sergeev.palindrom.app.api.v1.controller.response.UserResponse;
import com.sergeev.palindrom.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody String userName) {
        UserResponse registeredUser = userService.register(userName);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }


}
