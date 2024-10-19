package com.eazybytes.repository;

import com.eazybytes.model.AccountTransactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, String> {

    @Query("select ac from AccountTransactions ac where ac.customer.id = ?1")
    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(Integer customerId);
}
