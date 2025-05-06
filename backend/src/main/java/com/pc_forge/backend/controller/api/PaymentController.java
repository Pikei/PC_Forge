package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import com.pc_forge.backend.controller.api.constants.UrlPath;
import com.pc_forge.backend.controller.exceptions.InvalidOrderDataException;
import com.pc_forge.backend.controller.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler odbierający informację o powodzeniu i anulowaniu płatności
 */
@RestController
@RequestMapping(UrlPath.PAYMENT)
public class PaymentController {
    /**
     * Serwis odpowiedzialny za aktualizację statusu zamówienia
     */
    private final OrderService orderService;

    /**
     * Konstruktor kontrolera płatności.
     *
     * @param orderService Serwis zamówień.
     */
    public PaymentController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Metoda obsługująca sukces płatności. Ustawia status zamówienia na 2 - Opłacone.
     *
     * @param sessionId ID sesji płatności
     * @return Odpowiedź ze statusem HTTP 200 (OK) w przypadku sukcesu.
     * Jeśli nie udało się odnaleźć zamówienia po identyfikatorze sesji,
     * zwracana jest odpowiedź ze statusem HTTP 404 (NOT_FOUND)
     */
    @GetMapping(UrlPath.SUCCESS)
    public ResponseEntity<Void> paymentSuccess(@RequestParam(RequestParams.SESSION_ID) String sessionId) {
        try {
            orderService.paymentSucceeded(sessionId);
            return ResponseEntity.ok().build();
        } catch (InvalidOrderDataException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Metoda obsługująca anulowanie płatności. Zwraca informację o anulowaniu płatności.
     *
     * @return Odpowiedź ze statusem HTTP 200 (OK) i komunikatem o anulowaniu płatności.
     */
    @GetMapping(UrlPath.CANCEL)
    public ResponseEntity<String> paymentCancel() {
        return ResponseEntity.ok("Płatność anulowana");
    }
}
