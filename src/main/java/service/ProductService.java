package com.microservices.productservice.service;

import com.microservices.productservice.entity.Product;
import com.microservices.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
    // UPDATE
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());

        return productRepository.save(existingProduct);
    }

    // DELETE
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // PAGINATION + SORTING
    public Page<Product> getProductsWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return productRepository.findAll(pageable);
    }

    // JAVA STREAMS FILTER + TRANSFORM
    public List<Product> filterProductsByPrice(double minPrice) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getPrice() > minPrice)
                .map(product -> {
                    product.setName(product.getName().toUpperCase());
                    return product;
                })
                .toList();
    }
    public List<Product> getProductsAbovePrice(double price) {
        return productRepository.findProductsAbovePrice(price);
    }




}


