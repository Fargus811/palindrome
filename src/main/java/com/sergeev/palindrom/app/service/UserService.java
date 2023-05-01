package com.sergeev.palindrom.app.service;

import com.sergeev.palindrom.app.api.v1.controller.response.UserResponse;

public interface UserService {

    UserResponse register(String name);
}
