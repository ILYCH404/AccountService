package com.example.accountservice.service.impl;

import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.service.AccountService;
import com.example.accountservice.statistic.StatisticManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.accountservice.utils.AccountUtils.getTotalAmount;

/**
 * Возможно я придал большое значение данной формулировке, которая заключалась в
 * "Retrieves current balance or zero if addAmount() method was not called before for specified id"
 * Поскольку решил, что иметь 0 счет в аккаунте могут и те, где вызывался данный метод.
 *
 * Поэтому, думал сделать через реализацию с флагами и первоначально инициализации аккаунтов в файле, но затем было принято
 * решение создавать аккаунт и выводить нулевый или переданный amount, тем самым я не изменял тз сервиса, избежал ошибок и необходимость
 * инициализировать данными бд.
 *
 *
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(cacheNames = "account", key = "#id")
    public synchronized Long getAmount(Integer id) {
        log.info("serviceImpl getAmount {}", id);
        Optional<Account> account = repository.findById(id);
        StatisticManager.increaseReadOperation();
        if (account.isEmpty()) {
            repository.save(new Account(id, 0L));
        } else {
            return account.get().getAmount();
        }
        return 0L;
    }

    @Override
    @CachePut(cacheNames = "account", key = "#id")
    public synchronized Long addAmount(Integer id, Long amount) {
        log.info("service addAmount id: {}, amount: {}", id, amount);
        Optional<Account> account = repository.findById(id);
        long totalAmount = 0L;
        StatisticManager.increaseWriteOperation();
        if (account.isPresent()) {
            totalAmount = getTotalAmount(account, amount);
            account.get().setAmount(totalAmount);
            repository.save(account.get());
        } else {
            repository.save(new Account(id, amount));
        }
        return totalAmount;
    }
}
