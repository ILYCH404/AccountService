package com.example.accountservice.client;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

public class GetClient implements Runnable {
    private final RestTemplate restTemplate;
    private final AtomicInteger id;
    private final Integer from;
    private final Integer to;

    public GetClient(RestTemplate restTemplate, Integer from, Integer to) {
        this.restTemplate = restTemplate;
        id = new AtomicInteger(from);
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        while (true) {
            restTemplate.getForEntity(String.format("http://localhost:8080/account/getAmount/%d", id.getAndIncrement()), Long.class);
            if (id.get() > to) {
                id.set(from);
            }
        }
    }
}
