package com.sergeev.palindrom.app.service;

import com.sergeev.palindrom.app.api.v1.controller.request.UserPalindromeRequest;
import com.sergeev.palindrom.app.api.v1.controller.response.UserPalindromeResponse;
import com.sergeev.palindrom.app.api.v1.controller.response.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PalindromeService {

    UserPalindromeResponse checkPalindrome(UserPalindromeRequest  userPalindromeRequest);

    List<UserResponse> getLeaderBoard(Pageable pageable);
}
