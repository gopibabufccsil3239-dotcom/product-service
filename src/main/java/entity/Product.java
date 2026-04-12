package com.microservices.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")   // ✅ correct table name
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private int stock;
}