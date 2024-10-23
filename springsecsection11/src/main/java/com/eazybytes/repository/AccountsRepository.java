package com.eazybytes.repository;

import com.eazybytes.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Integer> {

    Optional<Accounts> findByCustomerId(Integer id);
}
