package com.sergeev.palindrom.app.repository;

import com.sergeev.palindrom.app.repository.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u ORDER BY u.score DESC")
    List<UserEntity> getLeaderBoard(Pageable pageable);

    boolean existsByName(String name);
}
