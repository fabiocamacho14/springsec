package com.eazybytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cards")
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", nullable = false)
    private Integer cardId;

    @Column(name = "card_number", length = 100, nullable = false)
    private String cardNumber;

    @Column(name = "card_type", length = 100, nullable = false)
    private String cardType;

    @Column(name = "total_limit", length = 11, nullable = false)
    private Integer totalLimit;

    @Column(name = "amount_used", length = 11, nullable = false)
    private Integer amountUsed;

    @Column(name = "available_amount", length = 11, nullable = false)
    private Integer availableAmount;

    @CreationTimestamp
    @Column(name = "create_dt")
    private LocalDateTime createDt;

//    Relationships
    @ManyToOne()
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_cards_customer"), nullable = false)
    private Customer customer;
}
