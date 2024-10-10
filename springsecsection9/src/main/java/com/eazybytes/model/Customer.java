package com.eazybytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", length = 11)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    @Column(name = "mobile_number", length = 20, nullable = false)
    private String mobileNumber;

    @Column(name = "pwd", length = 200, nullable = false)
    private String pwd;

    @Column(name = "role", length = 45, nullable = false)
    private String role;

    @Column(name = "create_dt")
    @CreationTimestamp
    private LocalDateTime create_dt;
}
