package com.pc_forge.backend.view.api.controller.product;

import com.pc_forge.backend.model.database.product.*;
import com.pc_forge.backend.controller.service.product.CommonProductService;
import com.pc_forge.backend.view.api.request.response.product.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CommonProductService productService;

    public ProductController(CommonProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
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

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductResponse>> getProductsByName(
            @PathVariable String name,
            @RequestParam(value = "category", required = false) String category
    ) {
        List<Product> products = productService.getProductsByName(name, category);
        return ResponseBuilder.generateProductList(products);
    }

    @GetMapping("/filter/search")
    public ResponseEntity<List<Object[]>> getProductsByCategoryFilter() {
        return ResponseEntity.ok(productService.getSearchFilter());

    }

    @GetMapping("/filter/search/{name}")
    public ResponseEntity<List<Object[]>> getProductsByCategoryFilter(@PathVariable String name) {
        return ResponseEntity.ok(productService.getSearchByNameFilter(name));
    }
}
