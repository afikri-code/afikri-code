package de.tramotech.model;

import org.junit.jupiter.api.Test;

import static de.tramotech.testutils.CustomerTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link Customer} class.
 * These tests serve as a sanity check to ensure that the Customer class works as expected.
 * Author: Ahmed Fikri
 */
class CustomerTest {



    /**
     * Tests that the fields of the {@link Customer} class can be read correctly.
     */
    @Test
    void readCustomer() {
        Customer actual = createCustomer();

        assertThat(actual.getCustomerNr()).isEqualTo(CUSTOMER_ID);
        assertThat(actual.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(actual.getLastName()).isEqualTo(LAST_NAME);
        assertThat(actual.getBirthday()).isEqualTo(BIRTHDAY);
        assertThat(actual.getEmail()).isEqualTo(EMAIL);
        assertThat(actual.getPhoneNumber()).isEqualTo(PHONE_NUMBER);
        assertThat(actual.getAddress()).isEqualTo(ADDRESS);
        assertThat(actual.getCity()).isEqualTo(CITY);
        assertThat(actual.getPostalCode()).isEqualTo(POSTAL_CODE);
        assertThat(actual.getAddressCountry()).isEqualTo(ADDRESS_COUNTRY);
        assertThat(actual.getPlaceOfBirthCountry()).isEqualTo(PLACE_OF_BIRTH_COUNTRY);
    }
}
