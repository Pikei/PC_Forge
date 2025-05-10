package com.pc_forge.backend.model.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pc_forge.backend.model.entity.user.Address;
import com.pc_forge.backend.model.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa będąca reprezentacją encji order_data z bazy danych. Zawiera informacje o złożonym zamówieniu,
 * takie jak informacje użytkownika, adres dostawy, data złożenia zamówienia, data dostawy, cena sumaryczna
 * status zamówienia oraz identyfikator sesji płatniczej. Klasa ta zawiera również zmapowaną listę szczegółów,
 * dotyczącą zamówienia, czyli jaki produkt i w ilu sztukach został zamówiony.
 */
@Getter
@Setter
@Entity
@Table(name = "order_data", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "order_data_order_id_key", columnNames = {"order_id"})
})
public class Order {
    /**
     * Identyfikator zamówienia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    /**
     * Obiekt użytkownika przypisany do zamówienia.
     */
    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Obiekt adresu dostawy przypisany do zamówienia.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private Address shippingAddress;

    /**
     * Data złożenia zamówienia.
     */
    @NotNull
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    /**
     * Data dostarczenia zamówienia.
     */
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    /**
     * Sumaryczna cena zamówionych produktów.
     */
    @NotNull
    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    /**
     * Status zamówienia.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "status")
    private OrderStatus status;

    /**
     * Identyfikator sesji płatniczej Stripe.
     */
    @JsonIgnore
    @Column(name = "session_id", length = Integer.MAX_VALUE)
    private String sessionId;

    /**
     * Metoda służąca do pozyskiwania adresu dostawy zamówienia, jako sformatowany ciąg znaków.
     *
     * @return Sformatowany adres dostawy.
     */
    public String getAddress() {
        String addr = shippingAddress.getCity() + ", ";
        addr += shippingAddress.getStreet() + " " + shippingAddress.getHouseNumber();
        if (shippingAddress.getApartmentNumber() != null) {
            addr += "/" + shippingAddress.getApartmentNumber();
        }
        addr += " " + shippingAddress.getPostalCode();
        return addr;
    }

    /**
     * Metoda pobierająca imię i nazwisko użytkownika.
     *
     * @return Imię i nazwisko użytkownika.
     */
    public String getCustomer() {
        return user.getFirstName() + " " + user.getLastName();
    }

    /**
     * Lista zmapowanych szczegółów dotyczących zamówienia.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
