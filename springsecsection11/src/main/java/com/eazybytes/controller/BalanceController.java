package com.eazybytes.controller;

import com.eazybytes.model.AccountTransactions;
import com.eazybytes.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam Integer id) {
        List<AccountTransactions> accountTransactionsList =
                accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);
        if (!accountTransactionsList.isEmpty()) {
            return accountTransactionsList;
        } else {
            return null;
        }
    }
}
