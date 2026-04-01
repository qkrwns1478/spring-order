package com.example.springorder.Product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /* 상품 등록 */
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    /* 상품 목록 조회 */
    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /* 상품 단건 조회 */
    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /* 상품 수정 */
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        Product new_product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        new_product.setName(product.getName());
        new_product.setPrice(product.getPrice());
        new_product.setQuantity(product.getQuantity());
        return productRepository.save(new_product);
    }

    /* 상품 이름 변경 */
    @PatchMapping("/{id}")
    public Product changeName(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        Product new_product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        new_product.setName(productRequest.getName());
        return productRepository.save(new_product);
    }

    /* 상품 삭제 */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        Product target_product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        productRepository.delete(target_product);
    }
}
