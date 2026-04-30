package com.microservices.productservice.kafka;

import com.microservices.productservice.dto.CartEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CartConsumer {

    @KafkaListener(topics = "cart-topic", groupId = "product-group")
    public void consume(CartEvent event) {

        System.out.println("📥 Event received in Product Service:");
        System.out.println("CartId: " + event.getCartId());
        System.out.println("ProductId: " + event.getProductId());
        System.out.println("Quantity: " + event.getQuantity());
    }
}