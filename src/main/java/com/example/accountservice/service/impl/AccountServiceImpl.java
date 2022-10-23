package com.example.accountservice.service.impl;

import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repository;

    @Override
    @Cacheable(cacheNames = "amount")
    public synchronized Long getAmount(Integer id) {
        log.info("getAmount {}", id);
        Optional<Account> account = repository.findById(id);

        if (account.isEmpty()) {
            repository.save(new Account(id, 0L));
        } else {
            return account.get().getAmount();
        }
        return 0L;
    }

    @Override
    @CachePut(cacheNames = "amount", key = "#id")
    public synchronized void addAmount(Integer id, Long amount) {
        log.info("addAmount id: {}, amount: {}", id, amount);
        Optional<Account> account = repository.findById(id);

        if (account.isPresent()) {
            account.get().setAmount(account.get().getAmount() + amount);
            repository.save(account.get());
        } else {
            repository.save(new Account(id, amount));
        }
    }
}
