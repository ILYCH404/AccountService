package com.example.accountservice.ustils;

import com.example.accountservice.model.Account;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class AccountUtils {
    public static boolean checkActive(Optional<Account> uncheckedAccount) {
        if (uncheckedAccount.isPresent()) {
            return uncheckedAccount.get().getActive();
        }
        return false;
    }
}
