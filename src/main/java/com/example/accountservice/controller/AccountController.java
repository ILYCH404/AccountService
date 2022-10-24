package com.example.accountservice.controller;

import com.example.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Tag(name = "AccountController")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService accountService) {
        this.service = accountService;
    }

    @PostMapping("/addAmount/{id}/{amount}")
    @Operation(summary = "addAmount")
    public ResponseEntity<HttpStatus> addAmountToAccount(@PathVariable Integer id, @PathVariable Long amount) {
        service.addAmount(id, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAmount/{id}")
    @Operation(summary = "getAmount")
    public Long getAccountAmount(@PathVariable Integer id) {
        return service.getAmount(id);
    }
}
