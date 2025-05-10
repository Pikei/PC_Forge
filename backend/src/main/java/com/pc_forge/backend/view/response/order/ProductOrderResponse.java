package com.pc_forge.backend.view.response.order;

import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją danych o zamówionym produkcie. Obiekt tej klasy zwracany jest jako odpowiedź
 * na żądanie HTTP pobrania danych zamówieniach złożonych przez użytkownika.
 */
@Getter
@Setter
public class ProductOrderResponse {
    /**
     * Identyfikator produktu. Umożliwia odnalezienie później szczegółowych informacji o produkcie
     * oraz znalezienie jego zdjęcia.
     */
    private Long productEan;

    /**
     * Nazwa produktu.
     */
    private String productName;

    /**
     * Cena produktu.
     */
    private Double productPrice;

    /**
     * Liczba zamówionych sztuk produktu.
     */
    private Integer productQuantity;

    /**
     * Kategoria produktu. Umożliwia odnalezienie później znalezienie jego zdjęcia.
     */
    private String productCategory;

    /**
     * Nazwa producenta. Umożliwia odnalezienie później znalezienie jego zdjęcia.
     */
    private String producer;
}
