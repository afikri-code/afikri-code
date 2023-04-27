package de.tramotech;

import de.tramotech.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.tramotech.testutils.CustomerTestUtils.createCustomer;
import static org.junit.jupiter.api.Assertions.*;

class CustomerCsvWriterTest {
    CustomerCsvWriter customerCsvWriter;

    @BeforeEach
    void setUp() {
        customerCsvWriter = new CustomerCsvWriter();
    }

    @Test
    void writeCustomersToCsv() {
        List<Customer> customers = List.of(createCustomer());
       System.getProperty("java.io.tmpdir");
        //customerCsvWriter.writeCustomersToCsv(customers, );
    }

}