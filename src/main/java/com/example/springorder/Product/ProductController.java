package com.example.springorder.Product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /* 상품 등록 */
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    /* 상품 목록 조회 */
    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    /* 상품 단건 조회 */
    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    /* 상품 수정 */
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    /* 상품 이름 변경 */
    @PatchMapping("/{id}/name")
    public Product changeName(@PathVariable Long id, @RequestBody ChangeNameRequest changeNameRequest) {
        return productService.changeName(id, changeNameRequest);
    }

    /* 상품 재고 변경 */
    @PatchMapping("/{id}/stock")
    public Product changeStock(@PathVariable Long id, @RequestBody ChangeStockRequest changeStockRequest) {
        return productService.changeStock(id, changeStockRequest);
    }

    /* 상품 삭제 */
    @DeleteMapping("/{id}")
    public Product deleteById(@PathVariable Long id) {
        return productService.deleteById(id);
    }
}
