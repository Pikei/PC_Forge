package com.pc_forge.backend.view.response.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) zamówienia użytkownika.
 * Obiekt tej klasy zwracany jest jako odpowiedź na żądanie HTTP pobrania danych zamówieniach złożonych przez użytkownika.
 */
@Getter
@Setter
public class OrderResponse {
    /**
     * Identyfikator zamówienia.
     */
    private Long id;

    /**
     * Imię i nazwisko użytkownika.
     */
    private String customer;

    /**
     * Sformatowany adres dostawy.
     */
    private String shippingAddress;

    /**
     * Status zamówienia.
     */
    private String orderStatus;

    /**
     * Opis statusu zamówienia.
     */
    private String orderStatusDescription;

    /**
     * Data złożenia zamówienia.
     */
    private LocalDate orderDate;

    /**
     * Data dostawy zamówienia.
     */
    private LocalDate deliveryDate;

    /**
     * Łączny koszt zamówienia.
     */
    private Double totalCost;

    /**
     * Lista obiektów klasy {@link ProductOrderResponse}, będących uproszczoną reprezentacją danych o zamówionych produktach.
     */
    private List<ProductOrderResponse> orderedProducts;
}
