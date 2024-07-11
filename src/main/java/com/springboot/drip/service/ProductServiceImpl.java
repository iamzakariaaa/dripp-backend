package com.springboot.drip.service;


import com.springboot.drip.model.Product;
import com.springboot.drip.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Product getProductById(Long productId){
        Optional<Product> product = productRepository.findById(productId);
        return product.get();
    }
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @Override
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

}
