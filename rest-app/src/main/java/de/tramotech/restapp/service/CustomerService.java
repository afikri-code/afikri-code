package de.tramotech.restapp.service;

import de.tramotech.restapp.dto.CustomerDto;
import de.tramotech.restapp.dto.DtoParser;
import de.tramotech.restapp.model.Customer;
import de.tramotech.restapp.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


/**
 * A service class for managing customer entities.
 * Author: Ahmed Fikri
 */
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;

    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("entity with id %d not found", id)));
        return modelMapper.map(customer, CustomerDto.class);
    }

    public CustomerDto getCustomerByCustomerNr(String customerNr) {
        Customer customer = customerRepository.findCustomerByCustomerNr(customerNr)
                .orElseThrow(() -> new NoSuchElementException(String.format("entity with customer number %s not found", customerNr)));
        return modelMapper.map(customer, CustomerDto.class);
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(modelMapper.map(customerDto, Customer.class));
        return modelMapper.map(customer, CustomerDto.class);
    }

    public void deleteCustomerById(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Customer not found for id :: " + id, e);
        }
    }


    public void updateCustomer(CustomerDto customerDto) {
        customerRepository.save(modelMapper.map(customerDto, Customer.class));
    }

    public List<CustomerDto> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    public void deleteCustomerByCustomerNr(String customerNr) {
        Customer customer = customerRepository.findCustomerByCustomerNr(customerNr)
                .orElseThrow(() -> new NoSuchElementException(String.format("entity with customerNr %s not found", customerNr)));
        customerRepository.deleteById(customer.getId());
    }

    public void saveAllCustomers(List<String> lines) {
        List<Customer> list = lines.stream()
                .map(str -> DtoParser.parseCustomerFromRawInput(str, ";"))
                .map(customerDto -> modelMapper.map(customerDto, Customer.class))
                .collect(Collectors.toList());
        customerRepository.saveAll(list);
    }
}
