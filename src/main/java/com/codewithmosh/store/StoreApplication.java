package com.codewithmosh.store;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
//        SpringApplication.run(StoreApplication.class, args);
        var orderService = new OrderService();
        orderService.setPaymentService(new PayPalPaymentService());
        orderService.placeOrder();
    }

}
