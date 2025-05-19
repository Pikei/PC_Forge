package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import com.pc_forge.backend.controller.api.constants.UrlPath;
import com.pc_forge.backend.controller.service.product.CommonProductService;
import com.pc_forge.backend.model.entity.product.Product;
import com.pc_forge.backend.view.response.product.ProductResponse;
import com.pc_forge.backend.view.util.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Klasa kontrolera odpowiedzialnego za obsługę żądań pobrania informacji o produktach, niezwiązanych z ich kategoriami,
 * takich jak wyszukiwanie po nazwie lub pobranie konkretnego produktu po jego ID
 */
@RestController
@CrossOrigin("http://localhost:4200/")
public class CommonProductController {
    /**
     * Serwis obsługujący logikę związaną ze wszystkimi produktami
     */
    private final CommonProductService productService;

    /**
     * @param productService serwis ogólny dla wszystkich kategorii produktów
     */
    public CommonProductController(CommonProductService productService) {
        this.productService = productService;
    }

    /**
     * Pobiera szczegółowe dane o produkcie na podstawie jego ID.
     *
     * @param id ID produktu
     * @return Odpowiedź HTTP zawierająca obiekt klasy dziedziczącej z {@link Product}, jeśli zostanie znaleziony.
     * Zwraca kod 404 (Not Found), jeśli produkt nie zostanie znaleziony
     * lub jeśli ID nie jest prawidłowym numerem.
     */
    @GetMapping(UrlPath.PRODUCT + "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        try {
            Long productId = Long.parseLong(id);
            Product product = productService.getProductById(productId);
            if (product == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(product);
        } catch (NumberFormatException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Wyszukuje produkty po nazwie, z opcjonalnym filtrowaniem po kategorii.
     *
     * @param name     Nazwa produktu do wyszukania.
     * @param category Kod kategorii produktu określony w {@link com.pc_forge.backend.controller.api.constants.ProductCategoryCode}.
     *                 Jest to parametr opcjonalny. Jego brak spowoduje wyszukanie produktów zawierających w nazwie podany ciąg znaków
     *                 spośród wszystkich kategorii. Podanie kodu kategorii zawęzi obszar poszukiwań.
     * @return Lista {@link ProductResponse}, czyli DTO produktów zawierających uproszczone dane, tworzone przez {@link ResponseBuilder}
     */
    @GetMapping(UrlPath.PRODUCT + UrlPath.SEARCH + "/{name}")
    public ResponseEntity<List<ProductResponse>> getProductsByName(
            @PathVariable String name,
            @RequestParam(value = RequestParams.PRODUCT_CATEGORY, required = false) String category
    ) {
        List<Product> products = productService.getProductsByName(name, category);
        return ResponseBuilder.generateProductList(products);
    }
}
