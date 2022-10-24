package com.example.accountservice.client;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class TestClient {
    private final Integer rCount;
    private final Integer wCount;
    private final List<Integer> list = new ArrayList<>();
    ExecutorService executorService;
    RestTemplate restTemplate = new RestTemplate();

    public TestClient(@Value("${test.get.rCount}") int rCount,
                      @Value("${test.add.wCount}") int wCount,
                      @Value("${test.id.fromList}") int fromList,
                      @Value("${test.id.toList}") int toList) {
        this.rCount = rCount;
        this.wCount = wCount;
        for (int i = fromList; i < toList; i++) {
            list.add(i++);
        }
    }

    public void startHighLoadTest() {
        executorService = Executors.newFixedThreadPool(rCount + wCount);
        for (int i = 0; i < wCount; i++) {
            executorService.submit(new AddClient(list, restTemplate));
        }
        for (int i = 0; i < rCount; i++) {
            executorService.submit(new GetClient(list, restTemplate));
        }
    }

    public void stopHighLoadTest() {
        executorService.shutdown();
    }
}
