package com.example.med_id.Med_Id.repository;

import com.example.med_id.Med_Id.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {
    @Query("FROM Token WHERE Token = ?1 AND IsExpired = false")
    Optional<Token> FindActiveToken(String token);

    @Query("FROM Token WHERE UserId = ?1 AND IsExpired = false")
    Optional<Token> FindTokenByUserId(Long id);
}