package com.microservices.productservice.controller;

import com.microservices.productservice.entity.Product;
import com.microservices.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // CREATE
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // READ ALL
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
    @GetMapping("/paginated")
    public Page<Product> getProducts(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        return productService.getProductsWithPagination(page, size, sortBy);
    }
    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam double minPrice) {

        return productService.filterProductsByPrice(minPrice);
    }
    @GetMapping("/price")
    public List<Product> getProductsAbovePrice(@RequestParam double price) {
        return productService.getProductsAbovePrice(price);
    }




}