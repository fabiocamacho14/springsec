package com.eazybytes.repository;

import com.eazybytes.model.NoticeDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDetailsRepository extends CrudRepository<NoticeDetails, Integer> {

//    @Query(value = "from NoticeDetails n")
    @Query(value = "from NoticeDetails n where CURDATE() between n.noticBegDt and  n.noticEndDt")
    List<NoticeDetails> findAllActiveNotices();
}
