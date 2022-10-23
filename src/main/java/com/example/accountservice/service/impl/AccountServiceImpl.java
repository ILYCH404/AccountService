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

import static com.example.accountservice.ustils.AccountUtils.checkActive;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repository;

    @Override
    @Cacheable(cacheNames = "amount")
    public synchronized Long getAmount(Integer id) {
        log.info("find amount {}", id);
        Optional<Account> account = repository.findById(id);
        if (account.isPresent()) {
            if (checkActive(account)) {
                return account.get().getAmount();
            } else {
                account.get().setActive(true);
            }
            repository.save(account.get());
        }
        return 0L;
    }

    @Override
    @CachePut(cacheNames = "amount", key = "#id")
    public synchronized void addAmount(Integer id, Long amount) {
        log.info("add amount to id = " + id + ", amount = " + amount);
        Optional<Account> account = repository.findById(id);
        if (checkActive(account)) {
            account.ifPresent(account1 -> account1.setAmount(account1.getAmount() + amount));
        } else {
            account.ifPresent(account1 -> account1.setActive(true));
            account.ifPresent(account1 -> account1.setAmount(account1.getAmount() + amount));
        }
        repository.save(account.get());
    }
}
