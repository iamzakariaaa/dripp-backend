package com.springboot.drip.service;

import com.springboot.drip.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product, MultipartFile file) throws IOException;
    Product updateProduct(Product product);
    void deleteProduct(Long productId);
    Product getProductById(Long productId);
    List<Product> getAllProducts();
}
