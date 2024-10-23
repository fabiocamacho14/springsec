package com.eazybytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Accounts {

    @Id
    @Column(name = "account_number", length = 11, nullable = false)
    private Integer accountNumber;

    @Column(name = "account_type", length = 100, nullable = false)
    private String accountType;

    @Column(name = "branch_address", length = 200, nullable = false)
    private String branchAddress;

    @CreationTimestamp
    @Column(name = "create_dt")
    private LocalDateTime createDt;

//    Relationships
    @OneToOne()
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_accounts_customer"), nullable = false)
    private Customer customer;
}
