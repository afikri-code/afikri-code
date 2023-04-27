package de.tramotech.restapp.service;

import de.tramotech.restapp.dto.DtoParser;
import de.tramotech.restapp.model.Customer;
import de.tramotech.restapp.model.Transaction;
import de.tramotech.restapp.repository.CustomerRepository;
import de.tramotech.restapp.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A service class for managing transaction entities.
 * Author: Ahmed Fikri
 */
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;

    public void saveAllTransactions(List<String> lines) {
        Map<String, Long> custIdMap = customerRepository.findAll()
                .stream().collect(Collectors.toMap(Customer::getCustomerNr, Customer::getId));
        List<Transaction> list = lines.stream()
                .map(str -> DtoParser.parseTransactionFromRawInput(str, ";"))
                .map(transactionDto -> modelMapper.map(transactionDto, Transaction.class))
                .map(transaction -> {
                    transaction.setCustomerId(custIdMap.get(transaction.getCustomerNr()));
                    return transaction;
                })
                .collect(Collectors.toList());
        transactionRepository.saveAll(list);
    }
}


