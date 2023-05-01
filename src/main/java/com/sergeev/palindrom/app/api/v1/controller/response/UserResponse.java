package com.sergeev.palindrom.app.api.v1.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private Long score;

}
