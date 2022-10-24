package com.example.accountservice.client;

import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GetClient implements Runnable {
    private final List<Integer> idList;
    private final RestTemplate restTemplate;
    private final AtomicInteger id;

    public GetClient(List<Integer> idList, RestTemplate restTemplate) {
        this.idList = idList;
        this.restTemplate = restTemplate;
        id = new AtomicInteger(0);
    }

    @Override
    public void run() {
        while (true) {
            restTemplate.getForEntity(String.format("http://localhost:8080/account/getAmount/%d", idList.get(id.getAndIncrement())), Long.class);
            if (id.get() == idList.size()) {
                id.set(0);
            }
        }
    }
}
