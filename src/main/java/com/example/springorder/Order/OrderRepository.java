package com.example.springorder.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @EntityGraph(attributePaths = "product")
    Page<Order> findAll(Pageable pageable);
}
