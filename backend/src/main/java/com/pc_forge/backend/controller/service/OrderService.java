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

/**
 * Serwis zamówień, odpowiedzialny za obsługę logiki związanej z pobieraniem listy zamówień, składaniem nowych
 * oraz anulowaniem złożonych zamówień.
 */
@Service
public class OrderService {
    /**
     * Repozytorium/DAO zamówień
     */
    private final OrderRepository orderRepository;

    /**
     * Repozytorium/DAO szczegółów dotyczących zamówienia
     */
    private final OrderDetailRepository orderDetailRepository;

    /**
     * Repozytorium/DAO statusów zamówień
     */
    private final OrderStatusRepository orderStatusRepository;

    /**
     * Repozytorium/DAO adresów
     */
    private final AddressRepository addressRepository;

    /**
     * Repozytorium/DAO koszyka zakupowego użytkownika
     */
    private final ShoppingCartRepository shoppingCartRepository;

    /**
     * Konstruktor wstrzykujący niezbędne zależności.
     *
     * @param orderRepository        Repozytorium/DAO zamówień
     * @param orderDetailRepository  Repozytorium/DAO szczegółów dotyczących zamówienia
     * @param orderStatusRepository  Repozytorium/DAO statusów zamówień
     * @param addressRepository      Repozytorium/DAO adresów
     * @param shoppingCartRepository Repozytorium/DAO koszyka zakupowego użytkownika
     */
    public OrderService(
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository,
            OrderStatusRepository orderStatusRepository,
            AddressRepository addressRepository,
            ShoppingCartRepository shoppingCartRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.addressRepository = addressRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    /**
     * Pobiera listę zamówień użytkownika
     *
     * @param user Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @return Lista zamówień użytkownika
     */
    public List<OrderResponse> getOrders(User user) {
        List<Order> orders = orderRepository.findByUser_Id(user.getId());
        List<OrderResponse> response = new ArrayList<>();
        for (Order order : orders) {
            response.add(getOrderResponse(order));
        }
        return response;
    }

    /**
     * Pobiera konkretne informacje o konkretnym zamówieniu użytkownika
     *
     * @param user    Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param orderId ID zamówienia
     * @return Obiekt zamówienia znaleziony w bazie danych
     * @throws InvalidOrderDataException gdy zamówienie nie istnieje
     */
    public Order getOrder(User user, Long orderId) throws InvalidOrderDataException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent() && order.get().getUser().getId().equals(user.getId())) {
            return order.get();
        } else {
            throw new InvalidOrderDataException("Order does not exist");
        }
    }

    /**
     * Tworzy nowe zamówienie dla użytkownika
     *
     * @param user        Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param addressBody Dane adresowe do zamówienia
     * @return Utworzone zamówienie
     */
    public Order newOrder(User user, AddressBody addressBody) throws InvalidOrderDataException {
        Address address = createAddress(addressBody);
        return createOrder(user, address);
    }

    /**
     * Tworzy lub pobiera istniejący adres na podstawie przekazanych danych
     *
     * @param addressBody dane adresowe użytkownika, gdzie ma zostać wysłane zamówienie
     */
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

    /**
     * Tworzy nowe zamówienie na podstawie zawartości koszyka
     *
     * @param user    Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param address Adres użytkownika, pod który ma być wysłane zamówienie
     */
    private Order createOrder(User user, Address address) throws InvalidOrderDataException {
        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(address);
        order.setOrderDate(LocalDate.now());
        List<ShoppingCart> productsInShoppingCart = shoppingCartRepository.findById_UserId(user.getId());
        if (productsInShoppingCart.isEmpty()) {
            throw new InvalidOrderDataException("Shopping cart of user \"" + user.getUsername() + "\" is empty");
        }
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
        return orderRepository.save(order);
    }

    /**
     * Konwertuje encję zamówienia na obiekt klasy {@link OrderResponse}, będącej DTO zamówienia.
     *
     * @param order obiekt zamówienia
     */
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

    /**
     * Generuje uproszczoną reprezentację danych o produkcie na podstawie przekazanego obiektu klasy {@link OrderDetail}
     *
     * @param orderDetail obiekt klasy {@link OrderDetail}, zawierający informację o zamówionym produkcie i jego liczbie
     */
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

    /**
     * Usuwa wszystkie zamówienia użytkownika. Używany tylko podczas usuwania konta użytkownika.
     *
     * @param user Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     */
    @Transactional
    public void deleteOrder(User user) {
        List<Order> orders = orderRepository.findByUser_Id(user.getId());
        for (Order order : orders) {
            orderDetailRepository.deleteByOrder(order.getId());
            orderRepository.delete(order);
        }
    }

    /**
     * Metoda służąca do anulowania zamówień. Jeśli zamówienie zostało złożone, ale nie opłacone i zostało anulowane,
     * to zostaje ono trwale usunięte z bazy danych. Jeśli zamówienie ma inny status niż "Opłacone"
     * i nie zostało ono już dostarczone albo anulowane wcześniej, ustawiany jest status na "Anulowane"
     *
     * @param user    Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param orderId ID zamówienia do anulowania
     * @return {@code true}, jeśli zamówienie zostało anulowane, {@code false} jeśli zostało usunięte
     * @throws InvalidOrderDataException w przypadku, gdy zamówienia nie można anulować
     */
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

    /**
     * Metoda wywoływana po udanej płatności. Aktualizuje status zamówienia.
     *
     * @param sessionId ID sesji płatności
     * @throws InvalidOrderDataException gdy zamówienie nie istnieje
     */
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