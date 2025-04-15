package com.pc_forge.backend.model.user.repository;

import com.pc_forge.backend.model.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}