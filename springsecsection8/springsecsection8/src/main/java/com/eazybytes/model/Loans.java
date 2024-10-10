package com.eazybytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "loans")
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loanNumber")
    private Integer loanNumber;

    @Column(name = "start_dt", nullable = false)
    private LocalDateTime startDt;

    @Column(name = "loan_type", nullable = false)
    private String loanType;

    @Column(name = "total_loan", length = 11, nullable = false)
    private Integer totalLoan;

    @Column(name = "amount_paid", length = 11, nullable = false)
    private Integer amountPaid;

    @Column(name = "outstanding_amount", length = 11, nullable = false)
    private Integer outstandingAmount;

    @Column(name = "creation_dt")
    @CreationTimestamp
    private LocalDateTime creationDt;

//    Relationships

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_loans_customer"), nullable = false)
    private Customer customer;
}
