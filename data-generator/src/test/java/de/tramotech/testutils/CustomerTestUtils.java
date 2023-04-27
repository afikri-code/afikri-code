package de.tramotech.testutils;

import model.Customer;

import java.time.LocalDate;

public class CustomerTestUtils {

    // test data
    public static final Long ID = 1L;
    public static final String CUSTOMER_ID = "007-56-9501";
    public static final String FIRST_NAME = "Anne";
    public static final String LAST_NAME = "Malek";
    public static final LocalDate BIRTHDAY = LocalDate.now();
    public static final String EMAIL = "isabelle.willwacher@gmail.com";
    public static final String PHONE_NUMBER = "+49-1566-5632453";
    public static final String ADDRESS = "Fortunastr. 46c";
    public static final String CITY = "Alt Alena";
    public static final String POSTAL_CODE = "42130";
    public static final String ADDRESS_COUNTRY = "Germany";
    public static final String PLACE_OF_BIRTH_COUNTRY = "Frankreich";

    /**
     * Creates a new instance of the {@link Customer} class with test data.
     *
     * @return A new instance of the {@link Customer} class.
     */
    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setCustomerId(CUSTOMER_ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setBirthday(BIRTHDAY);
        customer.setEmail(EMAIL);
        customer.setPhoneNumber(PHONE_NUMBER);
        customer.setAddress(ADDRESS);
        customer.setCity(CITY);
        customer.setPostalCode(POSTAL_CODE);
        customer.setAddressCountry(ADDRESS_COUNTRY);
        customer.setPlaceOfBirthCountry(PLACE_OF_BIRTH_COUNTRY);
        return customer;
    }
}
