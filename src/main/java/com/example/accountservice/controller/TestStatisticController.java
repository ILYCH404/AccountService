package com.example.accountservice.controller;

import com.example.accountservice.client.TestClient;
import com.example.accountservice.statistic.StatisticManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * После выполнение getAmount для всех заданных id, запросы в секунду падают до нуля.
 * Это обусловлено тем, что все значения добавляют в кэш и достаются оттуда
 * */
@RestController
@RequestMapping("/account/test")
@Tag(name="TestStatisticController")
public class TestStatisticController {
    private final TestClient testClient;

    public TestStatisticController(TestClient testClient) {
        this.testClient = testClient;
    }

    @GetMapping("/start")
    @Operation(summary = "startTest")
    public void startTest() {
        testClient.startHighLoadTest();
    }


    @GetMapping("/dropStatistic")
    @Operation(summary = "dropStatistic")
    public void dropStatistic() {
        StatisticManager.resetStatistic();
    }

    @GetMapping("/totalStatistic")
    @Operation(summary = "totalStatistic")
    public void totalStatistic() {
        StatisticManager.getTotalStatistic();
    }

}
