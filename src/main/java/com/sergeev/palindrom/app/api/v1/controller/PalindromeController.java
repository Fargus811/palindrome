package com.sergeev.palindrom.app.api.v1.controller;

import com.sergeev.palindrom.app.api.v1.controller.request.UserPalindromeRequest;
import com.sergeev.palindrom.app.api.v1.controller.response.UserPalindromeResponse;
import com.sergeev.palindrom.app.api.v1.controller.response.UserResponse;
import com.sergeev.palindrom.app.service.PalindromeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/palindrome")
@AllArgsConstructor
public class PalindromeController {

    private final PalindromeService palindromeService;


    @PostMapping("/check")
    public ResponseEntity<UserPalindromeResponse> checkPalindrome(@RequestBody UserPalindromeRequest userPalindromeDto) {
        return ResponseEntity.ok(palindromeService.checkPalindrome(userPalindromeDto));

    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<UserResponse>> getLeaderboard(Pageable pageable) {
        List<UserResponse> leaderboard = palindromeService.getLeaderBoard(pageable);
        return ResponseEntity.ok(leaderboard);
    }
}
