package com.eazybytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "contact_messages")
public class ContactMessages {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private String contactId;

    @Column(name = "contact_name", length = 50, nullable = false)
    private String contactName;

    @Column(name = "contact_email", length = 100, nullable = false)
    private String contactEmail;

    @Column(name = "subject", length = 500, nullable = false)
    private String subject;

    @Column(name = "message", length = 2000, nullable = false)
    private String message;

    @Column(name = "create_dt")
    @CreationTimestamp
    private LocalDateTime createDt;
}
