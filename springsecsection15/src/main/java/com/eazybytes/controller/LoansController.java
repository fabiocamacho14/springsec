package com.eazybytes.controller;

import com.eazybytes.model.Loans;
import com.eazybytes.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestParam Integer customerId) {
        List<Loans> loansList = loanRepository.findByCustomerIdOrderByStartDt(customerId);
        if (!loansList.isEmpty()) {
            return loansList;
        } else {
            return null;
        }
    }
}
