package com.pc_forge.backend.model.user.repository;

import com.pc_forge.backend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}