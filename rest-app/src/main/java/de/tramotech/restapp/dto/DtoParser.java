package de.tramotech.restapp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for parsing model data to and from Dto objects.
 * Author: Ahmed Fikri
 */
public class DtoParser {

    public static CustomerDto parseCustomerFromRawInput(String rawInput, String separator) throws IllegalArgumentException {
        String[] values = rawInput.split(separator);
        if (values.length != 11) {
            throw new IllegalArgumentException("Invalid input format");
        }
        CustomerDto customerDto = new CustomerDto();
        try {
            customerDto.setCustomerNr(values[0]);
            customerDto.setFirstName(values[1]);
            customerDto.setLastName(values[2]);
            customerDto.setEmail(values[4]);
            customerDto.setPhoneNumber(values[5]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid input format", ex);
        }
        return customerDto;
    }

    public static TransactionDto parseTransactionFromRawInput(String rawInput, String separator) throws IllegalArgumentException {
        String[] values = rawInput.split(separator);
        System.out.println(rawInput);
        System.out.println(values.length);
        if (values.length != 5) {
            throw new IllegalArgumentException("Invalid input format");
        }
        TransactionDto transactionDto = new TransactionDto();
        try {
            transactionDto.setTrxNr(values[0]);
            transactionDto.setCustomerNr(values[1]);
            transactionDto.setCredit(Integer.parseInt(values[2]) == 1);
            transactionDto.setAmount(new BigDecimal(values[3].replace(",",".")));
            transactionDto.setDate(LocalDate.parse(values[4], DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException("Invalid input format", ex);
        }
        return transactionDto;
    }

}
