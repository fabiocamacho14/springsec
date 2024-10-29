package com.eazybytes.repository;

import com.eazybytes.model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, Integer> {

    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(Integer customerId);
}