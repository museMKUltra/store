package com.codewithmosh.store;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;

//@Service
public class OrderService {
    private PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
        System.out.println("Order service created");
    }

    @PostConstruct
    public void init() {
        System.out.println("Order service PostConstruct");
    }

    public void placeOrder() {
        paymentService.processPayment(10);
    }
}
