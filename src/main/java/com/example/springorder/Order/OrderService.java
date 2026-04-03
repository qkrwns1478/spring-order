package com.example.springorder.Order;

import com.example.springorder.Page.PageRequest;
import com.example.springorder.Product.Product;
import com.example.springorder.Product.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order create(OrderRequest orderRequest) {
        Product product = productRepository.findByIdForUpdate(orderRequest.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (product.getStock() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        product.setStock(product.getStock() - 1);

        Order order = Order.builder()
                .product(product)
                .build();
        return orderRepository.save(order);
    }

    public OrderResponse findById(Long id) {
        Order order = getOrderOrThrow(id);

        if (order.getProduct() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return OrderResponse.from(order);
    }

    public List<OrderResponse> getOrders(PageRequest pageRequest) {
        int pageNo = pageRequest.getPage();
        int size = pageRequest.getSize();
        String sortBy = pageRequest.getSortBy();
        boolean isAsc = pageRequest.isAsc();

        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageNo, size, sort);

        Page<OrderResponse> page = orderRepository.findAll(pageable)
                .map(OrderResponse::from);

        return page.getContent();
    }

    private Order getOrderOrThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}