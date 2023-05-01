package com.sergeev.palindrom.app.service.impl;

import com.sergeev.palindrom.app.api.v1.controller.response.UserResponse;
import com.sergeev.palindrom.app.repository.UserRepository;
import com.sergeev.palindrom.app.repository.entity.UserEntity;
import com.sergeev.palindrom.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final static long DEFAULT_SCORE = 0L;
    private final UserRepository userRepository;

    public UserResponse register(String name) {
        if (userRepository.existsByName(name)) {
            throw new IllegalArgumentException("User with name " + name + " already exists");
        }

        UserEntity newUser = UserEntity.builder().name(name).score(DEFAULT_SCORE).build();
        return getResponseFromEntity(userRepository.save(newUser));
    }

    private UserResponse getResponseFromEntity(UserEntity userEntity){
        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .score(userEntity.getScore())
                .build();
    }

}
