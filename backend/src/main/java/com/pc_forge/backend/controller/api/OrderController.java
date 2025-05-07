package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import com.pc_forge.backend.controller.api.constants.UrlPath;
import com.pc_forge.backend.controller.exceptions.InvalidOrderDataException;
import com.pc_forge.backend.controller.exceptions.PaymentException;
import com.pc_forge.backend.controller.service.OrderService;
import com.pc_forge.backend.controller.service.PaymentService;
import com.pc_forge.backend.model.entity.order.Order;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.view.body.order.AddressBody;
import com.pc_forge.backend.view.response.order.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontroler obsługujący żądania związane z zamówieniami użytkowników.
 * Umożliwia tworzenie nowych zamówień, pobieranie historii zamówień,
 * ponawianie płatności za istniejące zamówienia oraz anulowanie zamówień.
 * Integruje się z serwisami {@link OrderService} do zarządzania logiką zamówień
 * oraz {@link PaymentService} do obsługi procesów płatności.
 */
@RestController
@RequestMapping(UrlPath.ORDER)
public class OrderController {

    /**
     * Serwis obsługujący logikę związaną z zamówieniami
     */
    private final OrderService orderService;

    /**
     * Serwis służący do stworzenia sesji płatniczej i zwrotu środków w przypadku anulowania zamówienia
     */
    private final PaymentService paymentService;

    /**
     * Konstruktor wstrzykujący zależności serwisu zamówień i płatności
     *
     * @param orderService   serwis zamówień
     * @param paymentService serwis płatności
     */
    public OrderController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    /**
     * Pobiera listę wszystkich zamówień dla uwierzytelnionego użytkownika.
     *
     * @param user Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @return Odpowiedź zawierająca listę {@link OrderResponse} będących DTO dla zamówień użytkownika
     * ze statusem HTTP 200 (OK) w przypadku sukcesu.
     * Jeśli użytkownik nie jest uwierzytelniony, zwraca status HTTP 401 (Unauthorized).
     */
    @GetMapping(UrlPath.ALL)
    public ResponseEntity<List<OrderResponse>> getOrder(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.getOrders(user));
    }

    /**
     * Tworzy nowe zamówienie na podstawie produktów znajdujących się w koszyku użytkownika i zwraca adres url do utworzonej sesji płatności.
     *
     * @param user        Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param addressBody Dane adresowe użytkownika niezbędne do wysyłki złożonego zamówienia
     * @return Odpowiedź zawierająca adres URL do utworzonej sesji płatności ze statusem HTTP 200 (OK)
     * w przypadku powodzenia utworzenia sesji płatności lub status HTTP 500 (INTERNAL_SERVER_ERROR) w razie niepowodzenia
     */
    @PostMapping(UrlPath.NEW)
    public ResponseEntity<String> createOrder(@AuthenticationPrincipal User user, @Valid @RequestBody AddressBody addressBody) {
        try {
            Order order = orderService.newOrder(user, addressBody);
            String paymentUrl = paymentService.createPaymentSession(order);
            return ResponseEntity.ok(paymentUrl);
        } catch (PaymentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Tworzy nową sesję płatności dla zamówienia, które nie zostało jeszcze opłacone.
     *
     * @param user    Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param orderId identyfikator zamówienia
     * @return Odpowiedź zawierająca adres URL do utworzonej sesji płatności ze statusem HTTP 200 (OK)
     * w przypadku powodzenia utworzenia sesji płatności lub status HTTP 500 (INTERNAL_SERVER_ERROR) w razie niepowodzenia
     */
    @PostMapping(UrlPath.PAYMENT)
    public ResponseEntity<String> payOrder(@AuthenticationPrincipal User user, @RequestParam(RequestParams.ORDER_ID) Long orderId) {
        Order order;
        try {
            order = orderService.getOrder(user, orderId);
            String paymentUrl = paymentService.createPaymentSession(order);
            return ResponseEntity.ok(paymentUrl);
        } catch (InvalidOrderDataException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (PaymentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Anuluje zamówienie o podanym ID dla uwierzytelnionego użytkownika.
     * Jeśli zamówienie kwalifikuje się do zwrotu środków, inicjuje proces zwrotu.
     *
     * @param user    Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security.
     * @param orderId ID zamówienia do anulowania, przekazywane jako parametr zapytania.
     * @return Odpowiedź zawierająca komunikat o wyniku anulowania zamówienia,
     * w tym informację o zwrocie środków, jeśli dotyczy, ze statusem HTTP 200 (OK).
     * W przypadku błędów zwraca odpowiedni status HTTP i komunikat.
     */
    @PostMapping(UrlPath.CANCEL)
    public ResponseEntity<String> cancelOrder(@AuthenticationPrincipal User user, @RequestParam(RequestParams.ORDER_ID) Long orderId) {
        try {
            boolean refund = orderService.cancelOrder(user, orderId);
            if (refund) {
                String returned = paymentService.refundPayment(orderId);
                return ResponseEntity.ok("Zwrócono środki w wysokości: " + returned);
            }
            return ResponseEntity.ok("Zamówienie zostało anulowane");
        } catch (PaymentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        } catch (InvalidOrderDataException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
