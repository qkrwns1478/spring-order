package com.example.springorder.Order;

import com.example.springorder.Product.Product;
import jakarta.persistence.*;
import lombok.*;

@Setter @Getter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Order() {}

    @Builder
    public Order(Product product) {
        this.product = product;
    }
}
