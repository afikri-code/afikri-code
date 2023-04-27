package de.tramotech.restapp.controller;

import de.tramotech.restapp.dto.CustomerDto;
import de.tramotech.restapp.exceptions.RestResponseEntityExceptionHandler;
import de.tramotech.restapp.model.Customer;
import de.tramotech.restapp.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.NoSuchElementException;
import java.util.Optional;

import static de.tramotech.restapp.CustomerTestHelper.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getCustomerById() throws Exception {
        Long id = 1L;
        CustomerDto customerDto = createCustomerDto();

        // Mock the behavior of the customer service
        when(customerService.getCustomerById(id)).thenReturn(customerDto);

        // Call the GET /customers/{id} endpoint
        mockMvc.perform(get("/api/customers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.intValue())))
                .andExpect(jsonPath("$.firstName", is(customerDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customerDto.getLastName())))
                .andExpect(jsonPath("$.email", is(customerDto.getEmail())));
    }

    @Test
    void getNonExistingCustomerById() throws Exception {
        Long id = 1L;

        // Mock the behavior of the customer service to throw a NoSuchElementException
        when(customerService.getCustomerById(id)).thenThrow(new NoSuchElementException());

        // Call the GET /customers/{id} endpoint
        mockMvc.perform(get("/api/customers/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Resource with id/customer number not found"));
    }

    @Test
    void getCustomerByCustomerNr() throws Exception {
        CustomerDto customerDto = createCustomerDto();

        // Mock the behavior of the customer service
        when(customerService.getCustomerByCustomerNr(CUSTOMER_NR_1)).thenReturn(customerDto);

        // Call the GET /customers/{id} endpoint
        mockMvc.perform(get("/api/customers/customerNr/{customerNr}", CUSTOMER_NR_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.customerNr", is(CUSTOMER_NR_1)))
                .andExpect(jsonPath("$.firstName", is(customerDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customerDto.getLastName())))
                .andExpect(jsonPath("$.email", is(customerDto.getEmail())));
    }

    @Test
    void getNonExistingCustomerByCustomerNr() throws Exception {
        Long id = 1L;

        // Mock the behavior of the customer service to throw a NoSuchElementException
        when(customerService.getCustomerByCustomerNr(CUSTOMER_NR_1)).thenThrow(new NoSuchElementException());

        // Call the GET /customers/{id} endpoint
        ResultActions h = mockMvc.perform(get("/api/customers/customerNr/{customerNr}", CUSTOMER_NR_1));
        mockMvc.perform(get("/api/customers/customerNr/{customerNr}", CUSTOMER_NR_1))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Resource with id/customer number not found"));
    }

    @Test
    void testDeleteCustomerById() {
        // Given
        CustomerDto customer = createCustomerDto1();
        doNothing().when(customerService).deleteCustomerById(customer.getId());

        // When
        ResponseEntity<Void> response = customerController.deleteCustomerById(customer.getId());

        // Then
        verify(customerService, times(1)).deleteCustomerById(customer.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteCustomerById_NonExistingCustomer() {
        // Given
        CustomerDto customer = createCustomerDto1();
        doThrow(new RuntimeException("Customer not found for id :: " + customer.getId())).when(customerService).deleteCustomerById(customer.getId());

        // When
        ResponseEntity<Void> response = customerController.deleteCustomerById(customer.getId());

        // Then
        verify(customerService, times(0)).deleteCustomerById(customer.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    private CustomerDto createCustomerDto() {
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
