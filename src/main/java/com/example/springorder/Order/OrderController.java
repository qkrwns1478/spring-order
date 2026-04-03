package com.example.springorder.Order;

import com.example.springorder.Page.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /* 주문 등록 */
    @PostMapping
    public Order create(@RequestBody OrderRequest orderRequest) {
        return orderService.create(orderRequest);
    }

    /* 주문 단건 조회 */
    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    /* 주문 전체 조회 */
    @GetMapping
    public List<OrderResponse> getOrders(@RequestBody PageRequest pageRequest) {
        return orderService.getOrders(pageRequest);
    }
}
