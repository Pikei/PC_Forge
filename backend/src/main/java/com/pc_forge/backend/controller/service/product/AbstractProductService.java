package com.pc_forge.backend.controller.service.product;

import com.pc_forge.backend.controller.filter.ProductFilter;
import com.pc_forge.backend.model.entity.product.Product;
import com.pc_forge.backend.model.repository.product.ProductRepository;
import com.pc_forge.backend.view.response.filter.ProductFilterResponse;

import java.util.*;
import java.util.function.Function;

/**
 * Klasa abstrakcyjna o typie generycznym klasy dziedziczącej po {@link Product}.
 * Jest to klasa bazowa dla wszystkich typów produktów, implementująca wspólną logikę dla każdej kategorii.
 *
 * @param <T> Typ generyczny, klasa dziedzicząca po {@link Product}
 */
public abstract class AbstractProductService<T extends Product> {
    /**
     * Repozytorium/DAO, produktu zawierające kwerendy wspólne dla wszystkich typów produktów.
     */
    protected final ProductRepository<T> productRepository;

    /**
     * Lista z zagnieżdżoną listą przechowującą obiekty klas dziedziczących po {@link Product}.
     * Listy zagnieżdżone przechowują obiekty, przefiltrowane według jednego parametru.
     * Lista nadrzędna jest zbiorem list produktów, gdzie każde pole, to efekt filtracji po innym parametrze HTTP.
     * Filtrowanie po wszystkich parametrach odbywa się poprzez wydobycie części wspólnej pomiędzy listami zagnieżdżonymi
     * w metodzie {@link #applyFilters()}
     */
    protected List<List<T>> filteredProducts = new ArrayList<>();

    /**
     * Konstruktor wstrzykujący repozytorium produktu
     *
     * @param productRepository Repozytorium/DAO
     */
    public AbstractProductService(ProductRepository<T> productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Metoda dodająca listy zagnieżdżone do pola {@link #filteredProducts},
     * na podstawie listy przekazanych filtrów i funkcji, będącej odwołaniem do repozytorium.
     * Z przekazanej listy filtrów jednego parametru (przykładowo lista producentów) pobierane są poszczególne wartości.
     * Następnie wartości te są pojedynczo przekazywane do otrzymanej funkcji. Funkcja zwraca listę produktów,
     * na podstawie wartości listy filtrów, która jest dodawana jako lista zagnieżdżona w {@link #filteredProducts}
     *
     * @param selectedValues Lista zawierająca wybrane parametry do filtrowania, przykładowo wybrani producenci
     * @param function       przekazana metoda z repozytorium produktu, służąca do pobrania listy produktów na podstawie parametru
     * @param <V>            Typ wartości przekazywanych do funkcji {@code function},
     *                       będący również typem wartości w liście {@code selectedValues}
     */
    protected <V> void filterByListParam(List<V> selectedValues, Function<V, List<T>> function) {
        if (selectedValues != null && !selectedValues.isEmpty()) {
            List<T> filtered = new ArrayList<>();
            for (V val : selectedValues) {
                filtered.addAll(function.apply(val));
            }
            filteredProducts.add(filtered);
        }
    }

    /**
     * Metoda dodająca listę zagnieżdżoną do pola {@link #filteredProducts} na podstawie parametru typu {@link Boolean}.
     * Filtr produktu bierze pod uwagę tylko pierwszy przekazany parametr w przypadku flagi,
     * dlatego przekazywana jest tutaj pojedyncza wartość {@code true/false} na podstawie której,
     * repozytorium zwraca listę obiektów klasy dziedziczącej po {@link Product}
     *
     * @param param    parametr typu {@link Boolean} otrzymany w żądaniu HTTP
     * @param function funkcja z repozytorium produktów zwracająca listę produktów na podstawie parametru {@code param}
     */
    protected void filterByBooleanParam(Boolean param, Function<Boolean, List<T>> function) {
        if (param != null) {
            filteredProducts.add(function.apply(param));
        }
    }

    /**
     * Metoda zwracająca listę produktów w danej kategorii, będących instancją klasy dziedziczącej po {@link Product}
     *
     * @param category Kod kategorii produktu opisany w {@link com.pc_forge.backend.controller.api.constants.ProductCategoryCode}
     * @return Lista produktów w danej kategorii
     */
    public List<T> getProductsInCategory(String category) {
        return productRepository.findByCategory(category);
    }

    /**
     * Metoda abstrakcyjna implementowana przez klasy dziedziczące. W nadpisanej metodzie w klasach dziedziczących
     * wywoływane są metody dodające listy zagnieżdżone do {@link #filteredProducts} na podstawie filtrów produktów,
     * czyli klas dziedziczących z {@link ProductFilter}
     *
     * @param filter obiekt filtra produktu
     * @return Lista przefiltrowanych produktów wg. parametrów HTTP. Filtracja odbywa się poprzez wydobycie części wspólnej
     * w listach zagnieżdżonych w {@link #filteredProducts}
     */
    public abstract List<T> getFilteredProducts(ProductFilter filter);

    protected void filterByProducers(String category, List<String> producers) {
        if (producers != null && !producers.isEmpty()) {
            List<T> filteredByProducers = new ArrayList<>();
            for (String producer : producers) {
                filteredByProducers.addAll(productRepository.findByProducerAndCategory(producer, category));
            }
            filteredProducts.add(filteredByProducers);
        }
    }

    protected void filterByName(String category, String name) {
        filteredProducts.add(productRepository.findByNameContainsIgnoreCaseAndCategory(name, category));
    }

    /**
     * Metoda filtruje produkty po cenie, pobierając wartości pomiędzy przekazanym minimum a maksimum.
     *
     * @param minPrice Cena minimalna odebrana z parametru HTTP. W przypadku gdy nie podano ceny minimalnej,
     *                 ustawiana jest najniższa cena produktu w danej kategorii.
     * @param maxPrice Cena maksymalna odebrana z parametru HTTP. W przypadku gdy nie podano ceny maksymalnej,
     *                 ustawiana jest najwyższa cena produktu w danej kategorii.
     * @param category kod kategorii opisany w {@link com.pc_forge.backend.controller.api.constants.ProductCategoryCode}
     */
    protected void filterByPrice(Double minPrice, Double maxPrice, String category) {
        if (minPrice == null && maxPrice == null) {
            return;
        }
        if (minPrice == null) {
            minPrice = productRepository.getMinPriceFilter(category);
        }
        if (maxPrice == null) {
            maxPrice = productRepository.getMaxPriceFilter(category);
        }
        filteredProducts.add(productRepository.findByPriceBetweenAndCategory(minPrice, maxPrice, category));
    }


    /**
     * Metoda aplikująca filtry. Spłaszcza {@link #filteredProducts} do jednowymiarowej listy produktów
     * wydobywając część wspólną spośród wszystkich list zagnieżdżonych.
     *
     * @return odfiltrowana lista produktów
     */
    protected List<T> applyFilters() {
        var filtered = filteredProducts.stream()
                .map(HashSet::new)
                .reduce((set1, set2) -> {
                    set1.retainAll(set2);
                    return set1;
                })
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);

        filtered.sort(Comparator.comparing(Product::getId));
        return filtered;
    }

    /**
     * Metoda abstrakcyjna, implementowana w klasach dziedziczących.
     * Zwraca DTO odpowiedzi filtra produktu, czyli klasy dziedziczącej z {@link ProductFilterResponse}
     *
     * @param <V> Typ generyczny, klasa dziedzicząca z {@link ProductFilterResponse}
     * @return obiekt klasy dziedziczącej z {@link ProductFilterResponse}
     * zawierający informację o dostępnych filtrach produktów w danej kategorii i ile produktów pasuje do tego filtra.
     */
    public abstract <V extends ProductFilterResponse> V getAvailableFilters();
}
