package com.pc_forge.backend.model.repository.user;

import com.pc_forge.backend.model.entity.user.ResetPasswordToken;
import com.pc_forge.backend.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    Optional<ResetPasswordToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("delete from ResetPasswordToken r where r.user = ?1")
    void deleteByUser(User user);
}