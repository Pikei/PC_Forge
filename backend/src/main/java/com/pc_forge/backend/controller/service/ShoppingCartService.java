package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.controller.exceptions.ProductDoesNotExistException;
import com.pc_forge.backend.model.entity.product.Product;
import com.pc_forge.backend.model.repository.product.CommonProductRepository;
import com.pc_forge.backend.model.entity.cart.ShoppingCart;
import com.pc_forge.backend.model.entity.cart.ShoppingCartId;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.model.repository.cart.ShoppingCartRepository;
import com.pc_forge.backend.view.response.order.ProductOrderResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CommonProductRepository commonProductRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, CommonProductRepository commonProductRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.commonProductRepository = commonProductRepository;
    }

    public void addProductToCart(User user, Long productId) {
        Optional<Product> optionalProduct = commonProductRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductDoesNotExistException("Product with id " + productId + " does not exist");
        }
        Optional<ShoppingCart> optionalProductInShoppingCart = shoppingCartRepository.findById(new ShoppingCartId(user.getId(), productId));
        if (optionalProductInShoppingCart.isEmpty()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setId(new ShoppingCartId(user.getId(), productId));
            shoppingCart.setUser(user);
            shoppingCart.setProduct(optionalProduct.get());
            shoppingCart.setQuantity(1);
            shoppingCartRepository.save(shoppingCart);
            return;
        }
        ShoppingCart productInShoppingCart = optionalProductInShoppingCart.get();
        productInShoppingCart.setQuantity(productInShoppingCart.getQuantity() + 1);
        shoppingCartRepository.save(productInShoppingCart);
    }

    public void removeProductFromCart(User user, Long productId) {
        Optional<Product> optionalProduct = commonProductRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductDoesNotExistException("Product with id " + productId + " does not exist");
        }
        Optional<ShoppingCart> optionalProductInShoppingCart = shoppingCartRepository.findById(new ShoppingCartId(user.getId(), productId));
        if (optionalProductInShoppingCart.isEmpty()) {
            throw new ProductDoesNotExistException("Product with id " + productId + " does not exist in shopping cart");
        }
        ShoppingCart productInShoppingCart = optionalProductInShoppingCart.get();
        if (productInShoppingCart.getQuantity() == 1) {
            shoppingCartRepository.delete(productInShoppingCart);
            return;
        }
        productInShoppingCart.setQuantity(productInShoppingCart.getQuantity() - 1);
        shoppingCartRepository.save(productInShoppingCart);
    }

    public List<ProductOrderResponse> getProductsInShoppingCart(User user) {
        List<ShoppingCart> productsInShoppingCart = shoppingCartRepository.findById_UserId(user.getId());
        List<ProductOrderResponse> productsInShoppingCartResponse = new ArrayList<>();
        for (ShoppingCart shoppingCart : productsInShoppingCart) {
            ProductOrderResponse productInShoppingCartResponse = getProductFromCart(shoppingCart);
            if (productInShoppingCartResponse != null) {
                productsInShoppingCartResponse.add(productInShoppingCartResponse);
            }
        }
        return productsInShoppingCartResponse;
    }

    private ProductOrderResponse getProductFromCart(ShoppingCart shoppingCart) {
        Optional<Product> optionalProduct = commonProductRepository.findById(shoppingCart.getProduct().getId());
        if (optionalProduct.isEmpty()) {
            return null;
        }
        Product product = optionalProduct.get();
        ProductOrderResponse response = new ProductOrderResponse();
        response.setProductEan(product.getId());
        response.setProductName(product.getName());
        response.setProductPrice(product.getPrice());
        response.setProducer(product.getProducer());
        response.setProductCategory(product.getCategory());
        response.setProductQuantity(shoppingCart.getQuantity());
        return response;
    }
}
