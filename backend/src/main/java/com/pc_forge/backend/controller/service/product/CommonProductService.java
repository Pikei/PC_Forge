package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.model.entity.product.Product;
import com.pc_forge.backend.model.repository.product.CommonProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Klasa serwisu wspólnego dla wszystkich typów produktów. Odpowiada za logikę żądań niepowiązanych z żadną kategorią.
 * Odpowiada za pobieranie danych za pomocą {@link #productRepository} podczas wyszukiwania produktu po nazwie
 * i pobierania informacji o konkretnym produkcie.
 */
@Service
public class CommonProductService {
    /**
     * Repozytorium/DAO produktu
     */
    private final CommonProductRepository productRepository;

    /**
     * Konstruktor serwisu produktu wstrzykujący repozytorium
     *
     * @param productRepository repozytorium/DAO produktu
     */
    public CommonProductService(CommonProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Metoda odpowiedzialna za pobranie szczegółowych informacji o produkcie.
     *
     * @param id identyfikator produktu
     * @return obiekt klasy dziedziczącej z {@link Product} lub {@code null}, jeśli nie udało się odnaleźć żądanego produktu
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Pobiera listę produktów zawierających w nazwie wskazane słowo.
     *
     * @param name     słowo kluczowe dopasowywane do nazwy produktu
     * @return lista produktów zawierających w nazwie wskazane słowo
     */
    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContainsIgnoreCase(name);
    }

    /**
     * Pobiera listę możliwych filtrów podczas wyszukiwania produktów (listę kategorii), które spełniają kryterium
     * posiadania w nazwie wskazanego słowa.
     *
     * @return lista możliwych filtrów podczas wyszukiwania produktów
     */
    public List<Object[]> getSearchByNameFilter(String name) {
        return productRepository.getCategorySearchFilter(name);
    }
}
