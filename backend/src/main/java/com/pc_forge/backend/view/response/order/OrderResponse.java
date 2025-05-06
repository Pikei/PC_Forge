package com.pc_forge.backend.view.response.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private String customer;
    private String shippingAddress;
    private String orderStatus;
    private String orderStatusDescription;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Double totalCost;
    private List<ProductOrderResponse> orderedProducts;
}
