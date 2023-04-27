package model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A class representing a transaction with a transaction number, customer number,
 * amount and date.
 * Author: Ahmed Fikri
 */
@Data
public class Transaction {
    private String transactionNr;
    private String customerNr;
    private boolean credit;
    private BigDecimal amount;
    private LocalDate date;
}
