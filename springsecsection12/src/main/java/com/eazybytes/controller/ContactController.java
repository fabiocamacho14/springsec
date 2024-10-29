package com.eazybytes.controller;

import com.eazybytes.model.ContactMessages;
import com.eazybytes.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/contact")
    public ContactMessages saveContactInquiryDetails(@RequestBody ContactMessages contactMessages) {
        contactMessages.setContactId(getServiceReqNumber().toString());
        return contactRepository.save(contactMessages);
    }

    public Integer getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return ranNum;
    }
}
