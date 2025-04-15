package com.pc_forge.backend.model.user.repository;

import com.pc_forge.backend.model.user.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
}