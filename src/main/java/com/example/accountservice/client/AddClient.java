package com.example.accountservice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class AddClient implements Runnable {
    private final List<Integer> idList;
    private final RestTemplate restTemplate;
    private Integer id;

    public AddClient(List<Integer> idList, RestTemplate restTemplate) {
        this.idList = idList;
        this.restTemplate = restTemplate;
        id = 0;
    }

    @Override
    public void run() {
        while (true) {
            restTemplate.postForLocation(String.format("http://localhost:8080/account/addAmount/%d/%d", idList.get(id), 5), Long.class);
            id++;
            if (id == idList.size()) {
                id = 0;
            }
        }
    }
}
