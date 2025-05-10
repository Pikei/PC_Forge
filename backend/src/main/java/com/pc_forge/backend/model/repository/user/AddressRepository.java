package com.pc_forge.backend.model.repository.user;

import com.pc_forge.backend.model.entity.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repozytorium/DAO dla {@link Address}. Zawiera niezbędne kwerendy pobierające odpowiednie dane z bazy danych.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
    /**
     * Kwerenda sprawdzająca, czy adres już znajduje się w bazie danych.
     *
     * @param city            Miasto
     * @param postalCode      Kod pocztowy
     * @param street          Ulica
     * @param houseNumber     Numer domu
     * @param apartmentNumber Numer mieszkania (opcjonalnie)
     * @return Obiekt klasy {@link Address} obudowany w {@link Optional}
     */
    @Query("""
            select a from Address a
            where a.city = :city and a.postalCode = :postalCode and a.street = :street and a.houseNumber = :houseNumber and a.apartmentNumber = :apartmentNumber""")
    Optional<Address> findIfExists(@Param("city") String city, @Param("postalCode") String postalCode, @Param("street") String street, @Param("houseNumber") String houseNumber, @Param("apartmentNumber") String apartmentNumber);
}