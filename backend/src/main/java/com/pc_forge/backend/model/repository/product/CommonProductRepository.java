package com.pc_forge.backend.model.repository.product;

import com.pc_forge.backend.model.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repozytorium/DAO produktu, wykorzystywane podczas operacji pobierania danych o konkretnym produkcie
 * i wyszukiwania produktów po nazwie.
 */
public interface CommonProductRepository extends JpaRepository<Product, Long> {
    /**
     * Kwerenda zwracająca listę produktów zawierających w nazwie wskazane słowo.
     *
     * @param name Ciąg znaków, do którego dopasowywana jest nazwa produktu.
     * @return Lista produktów zawierających w nazwie podany ciąg znaków.
     */
    List<Product> findByNameContainsIgnoreCase(String name);

    /**
     * Kwerenda zwracająca listę dostępnych kategorii produktów i liczbę produktów do nich należących.
     * Używane podczas wyszukiwania produktów po nazwie w danej kategorii.
     *
     * @return Lista zawierająca dostępne kategorie i liczby produktów
     */
    @Query("select p.category, count(*) from Product p where upper(p.name) like upper(concat('%', :name, '%')) group by p.category")
    List<Object[]> getCategorySearchFilter(@Param("name") String name);

}
