package com.sergeev.palindrom.app.service.impl;

import com.sergeev.palindrom.app.api.v1.controller.request.UserPalindromeRequest;
import com.sergeev.palindrom.app.api.v1.controller.response.UserPalindromeResponse;
import com.sergeev.palindrom.app.api.v1.controller.response.UserResponse;
import com.sergeev.palindrom.app.exception.NotFoundException;
import com.sergeev.palindrom.app.repository.UsedWordsRepository;
import com.sergeev.palindrom.app.repository.UserRepository;
import com.sergeev.palindrom.app.repository.entity.UserEntity;
import com.sergeev.palindrom.app.repository.entity.UserWordEntity;
import com.sergeev.palindrom.app.service.PalindromeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PalindromeServiceImpl implements PalindromeService {

    private final UserRepository userRepository;

    private final UsedWordsRepository usedWordsRepository;

    @Override
    public UserPalindromeResponse checkPalindrome(UserPalindromeRequest userPalindromeRequest) {
        UserEntity userEntity = userRepository.findById(userPalindromeRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        long score = userEntity.getScore();
        String lastAttemptDescription;
        UserPalindromeResponse userPalindromeResponse = new UserPalindromeResponse();
        userPalindromeResponse.setUserName(userEntity.getName());
        if (isPalindrome(userPalindromeRequest.getPalindrome())) {
            if (isWordUsedByUser(userEntity, userPalindromeRequest.getPalindrome())) {
                usedWordsRepository.save(UserWordEntity.builder().usedWord(userPalindromeRequest.getPalindrome())
                        .user(userEntity).build());
                updateUserScore(userEntity, score);
                lastAttemptDescription = "Palindrome! Score: " + userEntity.getScore();
            } else {
                lastAttemptDescription = "You have already used this palindrome!";
            }
        } else {
            lastAttemptDescription = "Not a palindrome!";
        }
        userPalindromeResponse.setPoints(userEntity.getScore());
        userPalindromeResponse.setLastAttemptDescription(lastAttemptDescription);
        return userPalindromeResponse;
    }

    private void updateUserScore(UserEntity userEntity, long score) {
        userEntity.setScore(score + 1);
        userRepository.save(userEntity);
    }

    public boolean isWordUsedByUser(UserEntity userEntity, String word) {
        return usedWordsRepository.findByUserAndUsedWord(userEntity, word).isEmpty();
    }

    public boolean isPalindrome(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        String reversedStr = new StringBuilder(word).reverse().toString();
        return word.equals(reversedStr);
    }

    public List<UserResponse> getLeaderBoard(Pageable pageable) {
        return userRepository.getLeaderBoard(pageable)
                .stream()
                .map(userEntity -> UserResponse.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .score(userEntity.getScore())
                        .build())
                .collect(Collectors.toList());
    }

}
