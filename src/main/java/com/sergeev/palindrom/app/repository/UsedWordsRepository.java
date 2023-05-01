package com.sergeev.palindrom.app.repository;

import com.sergeev.palindrom.app.repository.entity.UserEntity;
import com.sergeev.palindrom.app.repository.entity.UserWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsedWordsRepository extends JpaRepository<UserWord, Long> {

    Optional<UserEntity> findByUserAndUsedWord(UserEntity user, String word);
}

