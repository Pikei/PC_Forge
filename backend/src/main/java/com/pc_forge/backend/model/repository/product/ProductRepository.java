package com.pc_forge.backend.model.repository.product;

import com.pc_forge.backend.model.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository<T extends Product> extends JpaRepository<T, Long> {
    List<T> findByCategory(String category);

    List<T> findByProducerAndCategory(String producer, String category);

    List<T> findByPriceBetweenAndCategory(Double priceStart, Double priceEnd, String category);

    @Query("select p.producer, count(*) from Product p where p.category = :category group by p.producer order by p.producer")
    List<Object[]> getProducerFilter(@Param("category") String category);

    @Query("select p.price from Product p where p.category = :category order by p.price asc limit 1")
    Double getMinPriceFilter(@Param("category") String category);

    @Query("select p.price from Product p where p.category = :category order by p.price desc limit 1")
    Double getMaxPriceFilter(@Param("category") String category);

}