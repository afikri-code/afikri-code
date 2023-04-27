package de.tramotech.restapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) class for representing transaction data.
 * Author: Ahmed Fikri
 */
@Data
public class TransactionDto {
    Long id;
    @NotEmpty(message = "Transaction nummer is mandatory")
    private String trxNr;
    @NotEmpty(message = "Customer nummer is mandatory")
    private String customerNr;
    @NotEmpty(message = "credit flag is mandatory")
    private boolean credit;
    @NotEmpty(message = "amount is mandatory")
    private BigDecimal amount;
    @NotEmpty(message = "date is mandatory")
    private LocalDate date;
}
