package com.eazybytes.repository;

import com.eazybytes.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Integer> {

    List<Loans> findByCustomerIdOrderByStartDt(Integer customerId);
}