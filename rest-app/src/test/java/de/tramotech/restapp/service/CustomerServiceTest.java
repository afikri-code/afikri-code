package de.tramotech.restapp.service;

import de.tramotech.restapp.dto.CustomerDto;
import de.tramotech.restapp.model.Customer;
import de.tramotech.restapp.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.NoSuchElementException;
import java.util.Optional;

import static de.tramotech.restapp.CustomerTestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void getCustomerById() {
        //given
        CustomerDto expected = createCustomerDto1();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(createCustomerWithId()));
        when(modelMapper.map(createCustomerWithId(), CustomerDto.class)).thenReturn(expected);

        //when
        CustomerDto actual = customerService.getCustomerById(1L);

        //then
        verify(customerRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(), any());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getCustomerById_NoCustomerFound() {
        //given
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        //when
        NoSuchElementException actual = assertThrows(NoSuchElementException.class, () -> customerService.getCustomerById(1L));

        //then
        assertThat(actual).hasMessage("entity with id 1 not found");
    }

    @Test
    void getCustomerByCustNr() {
        //given
        CustomerDto expected = createCustomerDto1();
        when(customerRepository.findCustomerByCustomerNr(CUSTOMER_NR_1)).thenReturn(Optional.of(createCustomerWithId()));
        when(modelMapper.map(createCustomerWithId(), CustomerDto.class)).thenReturn(expected);

        //when
        CustomerDto actual = customerService.getCustomerByCustomerNr(CUSTOMER_NR_1);

        //then
        verify(customerRepository, times(1)).findCustomerByCustomerNr(CUSTOMER_NR_1);
        verify(modelMapper, times(1)).map(any(), any());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getCustomerCustNr_NoCustomerFound() {
        //given
        when(customerRepository.findCustomerByCustomerNr(CUSTOMER_NR_1)).thenReturn(Optional.ofNullable(null));

        //when
        NoSuchElementException actual = assertThrows(NoSuchElementException.class, () -> customerService.getCustomerByCustomerNr(CUSTOMER_NR_1));

        //then
        assertThat(actual).hasMessage(String.format("entity with customer number %s not found", CUSTOMER_NR_1));
    }

    @Test
    void saveCustomer() {
        CustomerDto customerDto = createCustomerDto1();

        customerService.saveCustomer(customerDto);

        //then
        verify(customerRepository, times(1)).save(any());
        verify(modelMapper, times(2)).map(any(), any());
    }

    @Test
    void deleteNonExistingCustomerById() {
        doThrow(EmptyResultDataAccessException.class).when(customerRepository).deleteById(1L);

        RuntimeException actual = assertThrows(RuntimeException.class, () -> customerService.deleteCustomerById(1L));

        //then
        verify(customerRepository, times(1)).deleteById(1L);
        assertThat(actual).hasMessage("Customer not found for id :: 1");
    }

    @Test
    void deleteExistingCustomerById() {
        doNothing().when(customerRepository).deleteById(1L);

        customerService.deleteCustomerById(1L);

        //then
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteExistingCustomerByCustNr() {
        when(customerRepository.findCustomerByCustomerNr(CUSTOMER_NR_1)).thenReturn(Optional.of(generateCustomer1()));
        doNothing().when(customerRepository).deleteById(1L);

        customerService.deleteCustomerByCustomerNr(CUSTOMER_NR_1);

        //then
        verify(customerRepository, times(1)).findCustomerByCustomerNr(CUSTOMER_NR_1);
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteNonExistingCustomerByCustNr() {
        when(customerRepository.findCustomerByCustomerNr(CUSTOMER_NR_1)).thenReturn(Optional.ofNullable(null));

        RuntimeException actual = assertThrows(RuntimeException.class, () -> customerService.deleteCustomerByCustomerNr(CUSTOMER_NR_1));

        //then
        verify(customerRepository, times(1)).findCustomerByCustomerNr(CUSTOMER_NR_1);
        assertThat(actual).hasMessage(String.format("entity with customerNr %s not found", CUSTOMER_NR_1));
    }


    @Test
    public void updateCustomer() {
        // Create a mock customer DTO object
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setFirstName("Max");
        Customer customer = new ModelMapper().map(customerDto, Customer.class);
        // Mock the update method of the customerRepository object using doAnswer
       /* doAnswer((a) -> {
            return customer;
        }).when(modelMapper).map(any(CustomerDto.class), Customer.class);
*/

        // Call the updateCustomer method of the CustomerService object
        customerService.updateCustomer(customerDto);

        // Verify that the update method of the CustomerRepository object was called once with the customer object
        verify(customerRepository, times(1)).save(any());
    }





}