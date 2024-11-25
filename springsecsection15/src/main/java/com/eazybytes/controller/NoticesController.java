package com.eazybytes.controller;

import com.eazybytes.model.NoticeDetails;
import com.eazybytes.repository.NoticeDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class NoticesController {

    @Autowired
    private NoticeDetailsRepository noticeDetailsRepository;

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeDetails>> getNotices() {
        List<NoticeDetails> noticeDetailsList = noticeDetailsRepository.findAllActiveNotices();
        if (!noticeDetailsList.isEmpty()) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(noticeDetailsList);
        } else {
            return null;
        }
    }
}