package com.eazybytes.controller;

import com.eazybytes.model.Accounts;
import com.eazybytes.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {

    AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam Integer accountNumber) {
        Optional<Accounts> accounts = accountsRepository.findByAccountNumber(accountNumber);
        if (accounts.isPresent()) {
            return accounts.get();
        } else {
            return null;
        }
    }
}
