package com.example.accountservice.utils;

import io.swagger.v3.oas.models.info.Info;

public class ApiUtils {
    public static Info apiInfo() {
        Info info = new Info();
        info
                .title("Исследование AccountService'а ")
                .description("iFuture test task");
        return info;
    }
}
