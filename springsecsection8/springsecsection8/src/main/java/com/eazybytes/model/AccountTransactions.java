package com.eazybytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "account_trasactions")
public class AccountTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", nullable = false)
    private Integer transactionId;

    @Column(name = "transaction_dt", nullable = false)
    private LocalDateTime transactionDt;

    @Column(name = "transaction_summary", length = 200, nullable = false)
    private String transactionSummary;

    @Column(name = "transaction_amt", length = 11, nullable = false)
    private Integer transactionAmt;

    @Column(name = "closing_balance", length = 11, nullable = false)
    private Integer closingBalance;

    @Column(name = "transaction_type", length = 100, nullable = false)
    private String transactionType;

    @Column(name = "create_dt")
    @CreationTimestamp
    private LocalDateTime createDt;

//    Relationships

    @ManyToOne()
    @JoinColumn(name = "account_number", foreignKey = @ForeignKey(name = "fk_account_transactions_accounts"), nullable = false)
    private Accounts accounts;

    @ManyToOne()
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_account_transactions_customer"), nullable = false)
    private Customer customer;
}
