package com.pc_forge.backend.view.api.controller;

import com.pc_forge.backend.model.database.product.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
public class ProductController {


    @GetMapping("all")
    public List<Product> getAllProducts() {
//        return productService.getProducts();
        return null;
    }

//    @GetMapping("{category}/{producer}/{ean}")
//    public ResponseEntity<Product> getProductData(@PathVariable String category,
//                                                  @PathVariable String producer,
//                                                  @PathVariable Long ean) {
//        Product product = productService.getProduct(ean);
//        if (product == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(product);
//    }
}
