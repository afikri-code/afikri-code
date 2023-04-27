package de.tramotech.restapp;

import de.tramotech.restapp.dto.CustomerDto;
import de.tramotech.restapp.model.Customer;

public class CustomerTestHelper {
    public static final String CUSTOMER_NR_1 = "123456789";
    public static final String CUSTOMER_NR_2 = "987654321";

    public static Customer generateCustomer1() {
        Customer result = new Customer();
        result.setId(1L);
        result.setCustomerNr(CUSTOMER_NR_1);
        result.setFirstName("Max");
        result.setLastName("Mustermann");
        result.setEmail("max@Mustermann.com");
        return result;
    }

    public static Customer generateCustomer2() {
        Customer customer = new Customer();
        customer.setCustomerNr(CUSTOMER_NR_2);
        customer.setFirstName("Hans");
        customer.setLastName("Peter");
        customer.setEmail("hans@Peter.com");
        return customer;
    }

    public static Customer createCustomerWithId() {
        Customer result = new Customer();
        result.setId(1L);
        result.setCustomerNr(CUSTOMER_NR_1);
        result.setFirstName("Max");
        result.setLastName("Mustermann");
        result.setEmail("max@mustermann.de");
        result.setPhoneNumber("123-456-78");
        return result;
    }

    public static CustomerDto createCustomerDto1() {
        CustomerDto result = new CustomerDto();
        result.setId(1L);
        result.setCustomerNr(CUSTOMER_NR_1);
        result.setFirstName("Max");
        result.setLastName("Mustermann");
        result.setEmail("max@mustermann.de");
        result.setPhoneNumber("123-456-78");
        return result;
    }
}
