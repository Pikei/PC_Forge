package com.pc_forge.backend.model.repository.configuration;

import com.pc_forge.backend.model.entity.configuration.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repozytorium/DAO dla {@link Configuration}. Zawiera niezbędne kwerendy pobierające odpowiednie dane z bazy danych.
 */
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    /**
     * Kwerenda służąca do znalezienia konfiguracji po jej nazwie i identyfikatorze użytkownika.
     *
     * @param id         Identyfikator użytkownika
     * @param configName Nazwa konfiguracji
     * @return Obiekt konfiguracji obudowany w {@link Optional}
     */
    Optional<Configuration> findByUser_IdAndConfigName(Long id, String configName);

    /**
     * Kwerenda znajdująca wszystkie konfiguracje użytkownika.
     *
     * @param id Identyfikator użytkownika
     * @return Lista konfiguracji użytkownika
     */
    List<Configuration> findByUser_Id(Long id);

}