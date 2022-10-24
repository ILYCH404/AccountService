package com.example.accountservice.statistic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
/**
 * Статистика отрабатывает верно, падение количества запросов на получение связано с кэшированием
 * На малое количество потоков статистика получения в секунду показывает 0 - это связано с тем, что запросы
 * проходят быстрее, чем за секунду. Если отобразить totalStatistic, то все будет верно
 */
@Service
@Slf4j
public class StatisticManager {

    private static Integer perSecondsGetRequest = 0;
    private static int totalGetRequest = 0;
    private static Integer perSecondsAddRequest = 0;
    private static int totalAddRequest = 0;


    public static void increaseWriteOperation() {
        StatisticManager.perSecondsAddRequest++;
        totalAddRequest++;
    }

    public static void increaseReadOperation() {
        StatisticManager.perSecondsGetRequest++;
        totalGetRequest++;
    }

    public static void resetStatistic() {
        totalGetRequest = 0;
        totalAddRequest = 0;
    }

    @Scheduled(fixedDelay = 1000)
    public static void perSecondsStatistic() {
        perSecondsAddRequest = 0;
        perSecondsGetRequest = 0;
    }

    public static void getTotalStatistic() {
        String def = "-";
        log.info("\n" + def.repeat(30) + "\n" +
                        "-total add request: {} \n" +
                        "-total get request: {} \n" +
                        def.repeat(30),
                totalAddRequest,
                totalGetRequest);
    }

    @Scheduled(fixedDelay = 4000)
    public static void getStatisticPerSeconds() {
        String def = "-";
        log.info("\n" + def.repeat(30) + "\n" +
                "-add request: {} perSeconds \n" +
                "-get request: {} perSeconds \n" +
                def.repeat(30),
                perSecondsAddRequest,
                perSecondsGetRequest);
    }


}
