package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.model.product.Product;
import com.pc_forge.backend.model.user.*;
import com.pc_forge.backend.model.user.repository.*;
import com.pc_forge.backend.view.body.AddressBody;
import com.pc_forge.backend.view.response.OrderResponse;
import com.pc_forge.backend.view.response.ProductOrderResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final AddressRepository addressRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, OrderStatusRepository orderStatusRepository, AddressRepository addressRepository, ShoppingCartRepository shoppingCartRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.addressRepository = addressRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<OrderResponse> getOrders(User user) {
        List<Order> orders = orderRepository.findByUser_Id(user.getId());
        List<OrderResponse> response = new ArrayList<>();
        for (Order order : orders) {
            response.add(getOrderResponse(order));
        }
        return response;
    }

    @Transactional
    public long newOrder(User user, AddressBody addressBody) {
        Address address = createAddress(addressBody);
        Order order = createOrder(user, address);
        saveOrderDetails(order);
        //TODO: API do płatności
        return shoppingCartRepository.deleteById_UserId(user.getId());
    }

    private void saveOrderDetails(Order order) {
        for (ShoppingCart cart : shoppingCartRepository.findById_UserId(order.getUser().getId())) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cart.getProduct());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setCost(cart.getProduct().getPrice() * cart.getQuantity());
            orderDetailRepository.save(orderDetail);
        }
    }

    private Order createOrder(User user, Address address) {
        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(address);
        order.setOrderDate(LocalDate.now());
        List<ShoppingCart> productsInShoppingCart = shoppingCartRepository.findById_UserId(user.getId());
        order.setTotalCost(productsInShoppingCart.stream()
                .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getQuantity())
                .sum());
        Optional<OrderStatus> statusOptional = orderStatusRepository.findById(1);
        statusOptional.ifPresent(order::setStatus);
        return orderRepository.save(order);
    }

    private Address createAddress(AddressBody addressBody) {
        Optional<Address> optionalAddress = addressRepository
                .findIfExists(addressBody.getCity(),
                        addressBody.getPostalCode(),
                        addressBody.getStreet(),
                        addressBody.getHouseNumber(),
                        addressBody.getApartmentNumber());
        if (optionalAddress.isPresent()) {
            return optionalAddress.get();
        }
        Address address = new Address();
        address.setCity(addressBody.getCity());
        address.setStreet(addressBody.getStreet());
        address.setHouseNumber(addressBody.getHouseNumber());
        address.setApartmentNumber(addressBody.getApartmentNumber());
        address.setPostalCode(addressBody.getPostalCode());
        return addressRepository.save(address);
    }

    private OrderResponse getOrderResponse(Order order) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_Id(order.getId());
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCustomer(order.getCustomer());
        orderResponse.setShippingAddress(order.getAddress());
        orderResponse.setOrderStatus(order.getStatus().getStatusName());
        orderResponse.setOrderStatusDescription(order.getStatus().getStatusDescription());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setDeliveryDate(order.getDeliveryDate());
        orderResponse.setTotalCost(order.getTotalCost());
        List<ProductOrderResponse> productOrder = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            productOrder.add(getProductOrderResponse(orderDetail));
        }
        orderResponse.setProductOrderResponses(productOrder);
        return orderResponse;
    }

    private ProductOrderResponse getProductOrderResponse(OrderDetail orderDetail) {
        ProductOrderResponse response = new ProductOrderResponse();
        Product product = orderDetail.getProduct();
        response.setProductEan(product.getId());
        response.setProductName(product.getName());
        response.setProductPrice(product.getPrice());
        response.setProductQuantity(orderDetail.getQuantity());
        response.setProductCategory(product.getCategory());
        response.setProducer(product.getProducer());
        return response;
    }
}
