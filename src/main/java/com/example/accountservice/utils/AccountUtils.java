package com.example.accountservice.utils;

import com.example.accountservice.model.Account;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class AccountUtils {

    public static Long getTotalAmount(Optional<Account> account, Long amount) {
        account.get().setAmount(account.get().getAmount() + amount);
        return account.get().getAmount();
    }
}
