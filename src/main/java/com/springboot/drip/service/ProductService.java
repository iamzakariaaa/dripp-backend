package com.springboot.drip.service;

import com.springboot.drip.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long productId);
    Product getProductById(Long productId);
    List<Product> getAllProducts();
}
