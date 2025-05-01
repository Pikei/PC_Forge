package com.pc_forge.backend.model.repository.product;

import com.pc_forge.backend.model.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommonProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainsIgnoreCase(String name);

    List<Product> findByNameContainsIgnoreCaseAndCategory(String name, String category);

    @Query("select p.category, count(*) from Product p group by p.category")
    List<Object[]> getCategoryFilter();

    @Query("select p.category, count(*) from Product p where upper(p.name) like upper(concat('%', :name, '%')) group by p.category")
    List<Object[]> getCategorySearchFilter(@Param("name") String name);

}
