package com.eazybytes.controller;

import com.eazybytes.model.AccountTransactions;
import com.eazybytes.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam Integer customerId) {
        List<AccountTransactions> accountTransactionsList =
                accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(customerId);
        if (!accountTransactionsList.isEmpty()) {
            return accountTransactionsList;
        } else {
            return null;
        }
    }
}
