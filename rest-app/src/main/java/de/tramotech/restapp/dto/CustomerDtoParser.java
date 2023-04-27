package de.tramotech.restapp.dto;

/**
 * Utility class for parsing customer data to and from CustomerDto objects.
 * Author: Ahmed Fikri
 */
public class CustomerDtoParser {

    public static CustomerDto parseFromRawInput(String rawInput, String separator) throws IllegalArgumentException {
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

}
