package com.pc_forge.backend.model.repository.user;

import com.pc_forge.backend.model.entity.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("""
            select a from Address a
            where a.city = :city and a.postalCode = :postalCode and a.street = :street and a.houseNumber = :houseNumber and a.apartmentNumber = :apartmentNumber""")
    Optional<Address> findIfExists(@Param("city") String city, @Param("postalCode") String postalCode, @Param("street") String street, @Param("houseNumber") String houseNumber, @Param("apartmentNumber") String apartmentNumber);
}