package com.example.accountservice.service.impl;

import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.accountservice.ustils.AccountUtils.checkActive;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repository;

    @Override
    public Long getAmount(Integer id) {
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
    public void addAmount(Integer id, Long value) {
        Optional<Account> account = repository.findById(id);
        if (checkActive(account)) {
            account.ifPresent(account1 -> account1.setAmount(account1.getAmount() + value));
        } else {
            account.ifPresent(account1 -> account1.setActive(true));
        }
        repository.save(account.get());
    }
}
