package com.example.accountservice.service;


public interface AccountService {

    Long getAmount(Integer id);

    /**
     * Мной было принято решение поменять возвращаемое значение в связи с тем, что я использую кэширование Spring,
     * которое может обновлять значение в кэше (CachePut), используя возвращаемое значение, а в случае возвращения void, оно оставляет
     * в параметре null
     *
     * Решения без изменения возвращаемого значения не нашел
     */
    Long addAmount(Integer id, Long value);
}
