package com.sergeev.palindrom.app.api.v1.controller.request;

import lombok.Data;

@Data
public class UserPalindromeRequest {

    private Long userId;
    private String palindrome;
}
