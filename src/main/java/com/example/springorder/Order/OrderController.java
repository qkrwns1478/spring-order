package com.example.springorder.Order;

import com.example.springorder.Product.Product;
import com.example.springorder.Product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    /* 주문 등록 */
    @PostMapping
    public Order create(@RequestBody OrderRequest orderRequest) {
        Product product = productRepository.findById(orderRequest.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Order order = Order.builder().product(product).build();
        return orderRepository.save(order);
    }

    /* 주문 단건 조회 */
    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (order.getProduct() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new OrderResponse(id, order.getProduct().getName());
    }

    /* 주문 전체 조회 */
    @GetMapping
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /* 주문 수정 */
    @PatchMapping ("/{id}")
    public Order update(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Product product = productRepository.findById(orderRequest.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        order.setProduct(product);
        return orderRepository.save(order);
    }
}
