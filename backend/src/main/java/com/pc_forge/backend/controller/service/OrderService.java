package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.controller.exceptions.InvalidOrderDataException;
import com.pc_forge.backend.model.entity.product.Product;
import com.pc_forge.backend.model.entity.user.Address;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.model.entity.order.Order;
import com.pc_forge.backend.model.entity.order.OrderDetail;
import com.pc_forge.backend.model.entity.order.OrderStatus;
import com.pc_forge.backend.model.entity.cart.ShoppingCart;
import com.pc_forge.backend.model.repository.cart.ShoppingCartRepository;
import com.pc_forge.backend.model.repository.order.OrderDetailRepository;
import com.pc_forge.backend.model.repository.order.OrderRepository;
import com.pc_forge.backend.model.repository.order.OrderStatusRepository;
import com.pc_forge.backend.model.repository.user.AddressRepository;
import com.pc_forge.backend.view.body.order.AddressBody;
import com.pc_forge.backend.view.response.order.OrderResponse;
import com.pc_forge.backend.view.response.order.ProductOrderResponse;
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

    public Order getOrder(User user, Long orderId) throws InvalidOrderDataException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent() && order.get().getUser().getId().equals(user.getId())) {
            return order.get();
        } else {
            throw new InvalidOrderDataException("Order does not exist");
        }
    }

    public Order newOrder(User user, AddressBody addressBody) {
        Address address = createAddress(addressBody);
        Order order = createOrder(user, address);
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
        return address;
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
        for (ShoppingCart cart : productsInShoppingCart) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cart.getProduct());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setCost(cart.getProduct().getPrice() * cart.getQuantity());
            order.getOrderDetails().add(orderDetail);
        }
        return order;
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
        orderResponse.setOrderedProducts(productOrder);
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

    @Transactional
    public void deleteOrder(User user) {
        List<Order> orders = orderRepository.findByUser_Id(user.getId());
        for (Order order : orders) {
            orderDetailRepository.deleteByOrder(order.getId());
            orderRepository.delete(order);
        }
    }

    @Transactional
    public boolean cancelOrder(User user, Long orderId) throws InvalidOrderDataException {
        Order order;
        order = getOrder(user, orderId);
        Optional<OrderStatus> statusWaitingForPaymentOptional = orderStatusRepository.findById(1);
        if (statusWaitingForPaymentOptional.isPresent()) {
            OrderStatus status = statusWaitingForPaymentOptional.get();
            if (order.getStatus().equals(status)) {
                orderRepository.delete(order);
                return false;
            }
        }
        if (orderStatusRepository.getCancellableStatuses().contains(order.getStatus())) {
            Optional<OrderStatus> statusCanceledOptional = orderStatusRepository.findById(7);
            statusCanceledOptional.ifPresent(order::setStatus);
            orderRepository.save(order);
            return true;
        }
        throw new InvalidOrderDataException("Order cannot be canceled");
    }

    @Transactional
    public void paymentSucceeded(String sessionId) throws InvalidOrderDataException {
        Optional<Order> optionalOrder = orderRepository.findOrderBySessionId(sessionId);
        if (optionalOrder.isEmpty()) {
            throw new InvalidOrderDataException("Order does not exists");
        }
        Order order = optionalOrder.get();
        Optional<OrderStatus> statusOptional = orderStatusRepository.findById(2);
        statusOptional.ifPresent(order::setStatus);
        orderRepository.save(order);
        shoppingCartRepository.deleteById_UserId(order.getUser().getId());
    }
}
