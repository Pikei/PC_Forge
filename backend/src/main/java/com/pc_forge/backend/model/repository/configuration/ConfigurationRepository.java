package com.pc_forge.backend.model.repository.configuration;

import com.pc_forge.backend.model.entity.configuration.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    Optional<Configuration> findByUser_IdAndConfigName(Integer id, String configName);

    List<Configuration> findByUser_Id(Integer id);

}