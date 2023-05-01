package com.sergeev.palindrom.app.repository;

import com.sergeev.palindrom.app.repository.entity.UserEntity;
import com.sergeev.palindrom.app.repository.entity.UserWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsedWordsRepository extends JpaRepository<UserWordEntity, Long> {

    Optional<UserWordEntity> findByUserAndUsedWord(UserEntity user, String word);
}

