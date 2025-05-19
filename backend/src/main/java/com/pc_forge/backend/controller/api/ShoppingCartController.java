package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.api.constants.UrlPath;
import com.pc_forge.backend.controller.exceptions.ProductDoesNotExistException;
import com.pc_forge.backend.controller.service.ShoppingCartService;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.view.body.order.ShoppingCartBody;
import com.pc_forge.backend.view.response.order.ProductOrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontroler koszyka użytkownika. Odpowiedzialny jest za przetwarzanie żądań dodawania, usuwania i czyszczenia koszyka
 * oraz wyświetlania listy produktów w nim zawartych.
 */
@RestController
@RequestMapping(UrlPath.SHOPPING_CART)
@CrossOrigin("http://localhost:4200/")
public class ShoppingCartController {
    /**
     * Serwis obsługujący logikę związaną z koszykiem użytkownika
     */
    private final ShoppingCartService shoppingCartService;

    /**
     * Konstruktor klasy kontrolera koszyka użytkownika
     *
     * @param shoppingCartService serwis koszyka użytkownika
     */
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Pobiera listę wszystkich produktów znajdujących się w koszyku
     *
     * @param user Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @return Lista DTO produktów zawierających uproszczone dane wspólne dla wszystkich kategorii
     */
    @GetMapping
    public ResponseEntity<List<ProductOrderResponse>> getShoppingCart(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(shoppingCartService.getProductsInShoppingCart(user));
    }

    /**
     * Dodaje wskazany produkt do koszyka
     *
     * @param user    Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param product Obiekt {@link ShoppingCartBody} zawierający identyfikator produktu
     * @return Pusta odpowiedź ze statusem HTTP 200 (OK) w przypadku pomyślnego dodania produktu do koszyka
     * lub odpowiedź ze statusem HTTP 400 (BAD_REQUEST) w przypadku podania nieprawidłowego identyfikatora produktu
     */
    @PostMapping(UrlPath.ADD)
    public ResponseEntity<Void> addItem(@AuthenticationPrincipal User user, @Valid @RequestBody ShoppingCartBody product) {
        try {
            shoppingCartService.addProductToCart(user, product.getProductId());
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Usuwa produkt z koszyka użytkownika.
     *
     * @param user    Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param product Obiekt {@link ShoppingCartBody} zawierający identyfikator produktu
     * @return Pusta odpowiedź ze statusem HTTP 200 (OK) w przypadku pomyślnego usunięcia produktu z koszyka
     * lub odpowiedź ze statusem HTTP 400 (BAD_REQUEST) w przypadku podania nieprawidłowego identyfikatora produktu
     */
    @PostMapping(UrlPath.REMOVE)
    public ResponseEntity<Void> removeItem(@Valid @AuthenticationPrincipal User user, @Valid @RequestBody ShoppingCartBody product) {
        try {
            shoppingCartService.removeProductFromCart(user, product.getProductId());
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Usuwa wszystkie produkty z koszyka użytkownika.
     *
     * @param user Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @return Pusta odpowiedź ze statusem HTTP 200 (OK) po usunięciu wszystkich produktów z koszyka
     */
    @PostMapping(UrlPath.CLEAR)
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal User user) {
        shoppingCartService.clearCart(user);
        return ResponseEntity.ok().build();
    }

    /**
     * Usuwa wszystkie instancje jednego produktu z koszyka
     *
     * @param user    Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param product Obiekt {@link ShoppingCartBody} zawierający identyfikator produktu
     * @return Pusta odpowiedź ze statusem HTTP 200 (OK) w przypadku pomyślnego usunięcia produktu z koszyka
     * lub odpowiedź ze statusem HTTP 400 (BAD_REQUEST) w przypadku podania nieprawidłowego identyfikatora produktu
     */
    @PostMapping(UrlPath.CLEAR + UrlPath.PRODUCT)
    public ResponseEntity<Void> clearProduct(@AuthenticationPrincipal User user, @Valid @RequestBody ShoppingCartBody product) {
        try {
            shoppingCartService.clearProduct(user, product.getProductId());
            return ResponseEntity.ok().build();
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
