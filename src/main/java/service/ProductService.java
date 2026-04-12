package com.microservices.productservice.service;

import com.microservices.productservice.entity.Product;
import com.microservices.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create product
    public Product createProduct(Product product) {
        return productRepository.save(product);// used to insert data into db
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();//returns all products from db
    }

    // Get product by id
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    // Validate stock
    public boolean validateStock(Long id, int quantity) {
        Product product = getProductById(id);
        return product.getStock() >= quantity;// returns true or false
    }
}