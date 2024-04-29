package com.springboot.drip.service;


import com.springboot.drip.model.Product;
import com.springboot.drip.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product){
        Product existingProduct = productRepository.findById(product.getId()).get();
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        Product updatedProduct = productRepository.save(existingProduct);
        return updatedProduct;
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
