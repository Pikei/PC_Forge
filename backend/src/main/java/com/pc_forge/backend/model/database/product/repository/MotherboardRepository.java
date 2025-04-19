package com.pc_forge.backend.model.database.product.repository;

import com.pc_forge.backend.model.database.product.Motherboard;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {

    @NonNull
    @Query("select m from Motherboard m join fetch m.standard join fetch m.socket where m.id = :id")
    @Override
    Optional<Motherboard> findById(@Param("id") Long ean);
}