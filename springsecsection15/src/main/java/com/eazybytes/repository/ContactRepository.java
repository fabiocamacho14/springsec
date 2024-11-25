package com.eazybytes.repository;

import com.eazybytes.model.ContactMessages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactMessages, Integer> {

}
