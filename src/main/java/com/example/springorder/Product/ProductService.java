package com.example.springorder.Product;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product create(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findByIsDeletedFalse();
    }

    public Product findById(Long id) {
        return getProductOrThrow(id);
    }

    @Transactional
    public Product update(Long id, Product product) {
        Product new_product = getProductOrThrow(id);
        new_product.setName(product.getName());
        new_product.setPrice(product.getPrice());
        new_product.setStock(product.getStock());
        return new_product;
    }

    @Transactional
    public Product changeName(Long id, ChangeNameRequest changeNameRequest) {
        Product product = getProductOrThrow(id);
        product.setName(changeNameRequest.getName());
        return product;
    }

    @Transactional
    public Product changeStock(Long id, ChangeStockRequest changeStockRequest) {
        Product product = getProductOrThrow(id);
        product.setStock(changeStockRequest.getStock());
        return product;
    }

    @Transactional
    public Product deleteById(Long id) {
        Product product = getProductOrThrow(id);
        product.setDeleted(true);
        return product;
    }

    private Product getProductOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}