package com.example.accountservice.controller;

import com.example.accountservice.client.TestClient;
import com.example.accountservice.statistic.StatisticManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/test")
@Slf4j
public class StatisticController {
    private final TestClient testClient;

    public StatisticController(TestClient testClient) {
        this.testClient = testClient;
    }

    @GetMapping("/start")
    public void startTest() {
        testClient.startHighLoadTest();
    }


    @GetMapping("/dropStatistic")
    public void dropStatistic() {
        StatisticManager.resetStatistic();
    }
    @GetMapping("/totalStatistic")
    public void totalStatistic() {
        StatisticManager.getTotalStatistic();
    }

}
