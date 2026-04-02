package com.example.springorder.Order;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class OrderResponse {
    private Long id;
    private String productName;

    public OrderResponse(Long id, String name) {
        this.id = id;
        this.productName = name;
    }

    public static OrderResponse from(Order order) {
        return new OrderResponse(order.getId(), order.getProduct().getName());
    }
}
