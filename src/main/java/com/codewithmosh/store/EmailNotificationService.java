package com.codewithmosh.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements NotificationService {
    @Value("${mail.host}")
    private String host;

    @Value("${mail.port:8080}")
    private String post;

    @Override
    public void send(String message, String recipientEmail) {
        System.out.println("Host: " + host);
        System.out.println("Post: " + post);
        System.out.println("Sending email: " + message + " to " + recipientEmail);
    }
}
