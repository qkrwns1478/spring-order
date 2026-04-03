//package com.example.springorder.util;
//
//import com.example.springorder.Order.Order;
//import com.example.springorder.Order.OrderRepository;
//import com.example.springorder.Product.Product;
//import com.example.springorder.Product.ProductRepository;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//
//@Component
//public class TestDataRunner implements ApplicationRunner {
//
//    private final OrderRepository orderRepository;
//    private final ProductRepository productRepository;
//
//    public TestDataRunner(OrderRepository orderRepository, ProductRepository productRepository) {
//        this.orderRepository = orderRepository;
//        this.productRepository = productRepository;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        String[] products = {"신발", "과자", "키보드", "휴지", "휴대폰", "앨범",
//                             "헤드폰", "이어폰", "노트북", "무선 이어폰", "모니터"};
//        for (String product : products) {
//            createProduct(product);
//        }
//        for (int i = 0; i < products.length; i++) {
//            createOrder(i+1);
//        }
//    }
//
//    private void createProduct(String name) {
//        Product product = new Product();
//        product.setName(name);
//        int price = getRandomNumber(100, 10000);
//        int stock = getRandomNumber(100, 10000);
//        product.setPrice(price);
//        product.setStock(stock);
//        productRepository.save(product);
//    }
//
//    private void createOrder(long productId) {
//        Order order = new Order();
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        order.setProduct(product);
//        orderRepository.save(order);
//    }
//
//    public int getRandomNumber(int min, int max) {
//        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
//    }
//}
