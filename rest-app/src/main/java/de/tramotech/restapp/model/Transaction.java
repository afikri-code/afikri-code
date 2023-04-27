package de.tramotech.restapp.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A domain model class that represents a transaction.
 * Author: Ahmed Fikri
 */
@Entity
@Data
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "trx_nr")
    private String trxNr;
    @Column(name = "customer_nr")
    private String customerNr;
    @Column(name = "is_credit")
    private boolean credit;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "customer_id")
    private Long customerId;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

 */


}
