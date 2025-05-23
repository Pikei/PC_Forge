package com.pc_forge.backend.model.repository.product;

import com.pc_forge.backend.model.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repozytorium/DAO zawierające kwerendy, z których korzystają wszystkie kategorie produktów.
 *
 * @param <T> Klasa dziedzicząca z {@link Product}
 */
public interface ProductRepository<T extends Product> extends JpaRepository<T, Long> {
    /**
     * Kwerenda znajdująca produkty po ich kategoriach
     *
     * @param category Kod kategorii produktu
     * @return Lista obiektów klas dziedziczących z {@link Product}
     */
    List<T> findByCategory(String category);

    /**
     * Kwerenda znajdująca produkty po ich nazwie i kategoriach
     *
     * @param category Kod kategorii produktu
     * @param name     Nazwa produktu
     * @return Lista obiektów klas dziedziczących z {@link Product}
     */
    List<T> findByNameContainsIgnoreCaseAndCategory(String name, String category);

    /**
     * Kwerenda znajdująca produkty po ich producentach i kategoriach
     *
     * @param category Kod kategorii produktu
     * @param producer Nazwa producenta
     * @return Lista obiektów klas dziedziczących z {@link Product}
     */
    List<T> findByProducerAndCategory(String producer, String category);

    /**
     * Kwerenda znajdująca produkty, których cena znajduje się pomiędzy przekazanym minimum i maksimum w danej kategorii
     *
     * @param category   Kod kategorii produktu
     * @param priceStart Cena minimalna
     * @param priceEnd   Cena maksymalna
     * @return Lista obiektów klas dziedziczących z {@link Product}
     */
    List<T> findByPriceBetweenAndCategory(Double priceStart, Double priceEnd, String category);

    /**
     * Kwerenda zwracająca listę producentów i liczbę produktów danego producenta w danej kategorii.
     * Używane podczas pobierania filtrów produktów.
     *
     * @param category Kod kategorii produktu
     * @return Lista zawierająca nazwy producentów i liczbę produktów danego producenta
     */
    @Query("select p.producer, count(*) from Product p where p.category = :category group by p.producer order by p.producer")
    List<Object[]> getProducerFilter(@Param("category") String category);

    /**
     * Kwerenda zwracająca najniższą cenę produktu w danej kategorii.
     *
     * @param category Kod kategorii produktu
     * @return Najniższa cena produktu w danej kategorii
     */
    @Query("select p.price from Product p where p.category = :category order by p.price asc limit 1")
    Double getMinPriceFilter(@Param("category") String category);

    /**
     * Kwerenda zwracająca najwyższą cenę produktu w danej kategorii.
     *
     * @param category Kod kategorii produktu
     * @return Najwyższa cena produktu w danej kategorii
     */
    @Query("select p.price from Product p where p.category = :category order by p.price desc limit 1")
    Double getMaxPriceFilter(@Param("category") String category);
}
