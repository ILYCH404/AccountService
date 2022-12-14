package com.example.accountservice.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Log4j2
public class TestClient {
    protected final Integer fromList;
    protected final Integer toList;
    private final Integer rCount;
    private final Integer wCount;
    ExecutorService executorService;
    RestTemplate restTemplate = new RestTemplate();

    public TestClient(@Value("${test.get.rCount}") int rCount,
                      @Value("${test.add.wCount}") int wCount,
                      @Value("${test.id.fromList}") int fromList,
                      @Value("${test.id.toList}") int toList) {
        this.rCount = rCount;
        this.wCount = wCount;
        this.fromList = fromList;
        this.toList = toList;
    }

    public void startHighLoadTest() {
        log.info("Start high load test");
        executorService = Executors.newFixedThreadPool(rCount + wCount);
        for (int i = 0; i < rCount; i++) {
            executorService.submit(new GetClient(restTemplate, fromList, toList));
        }
        for (int i = 0; i < wCount; i++) {
            executorService.submit(new AddClient(restTemplate, fromList, toList));
        }
    }


}
