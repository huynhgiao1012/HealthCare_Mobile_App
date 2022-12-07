package com.healthcareapp.healthcareappbackend.controller;

import com.healthcareapp.healthcareappbackend.dto.AccountDto;
import com.healthcareapp.healthcareappbackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PutMapping(path = "/getAccountByIdCard")
    public AccountDto getAccountByIdCard(@RequestBody AccountDto accountDtoInput) {
        AccountDto account = accountService.findAccountByIdCard(accountDtoInput.getIdCard());

        if (!account.getPassword().equals(accountDtoInput.getPassword())) {
            return null;
        }

        return account;
    }

    @PostMapping(path = "/signup")
    public AccountDto signUp(@RequestBody AccountDto accountDtoInput) {
        return accountService.save(accountDtoInput);
    }

    @PutMapping(path = "/forgotPassword")
    public AccountDto forgotPassword(@RequestBody AccountDto accountDtoInput) {
        AccountDto account = accountService.findAccountByIdCard(accountDtoInput.getIdCard());

        return accountService.save(account);
    }
}
