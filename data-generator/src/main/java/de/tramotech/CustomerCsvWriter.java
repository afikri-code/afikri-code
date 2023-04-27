package de.tramotech;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

import model.Customer;

/**
 * This class provides a utility method for writing a list of Customer objects to a CSV file.
 * Author: Ahmed Fikri
 */
public class CustomerCsvWriter {

    private final String[] HEADER = {"customerId", "firstName", "lastName", "birthday", "email", "phoneNumber", "address", "city", "postalCode", "address_country", "place_of_birth_country"};

    /**
     * Writes a list of Customer objects to a CSV file at the specified path.
     *
     * @param customers the list of Customer objects to write to the CSV file
     * @param filePath the path of the CSV file to write to
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void writeCustomersToCsv(List<Customer> customers, String filePath) throws IOException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Function<Customer, String[]> mapper = customer -> new String[]{
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthday().format(dateFormatter),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getCity(),
                customer.getPostalCode(),
                customer.getAddressCountry(),
                customer.getPlaceOfBirthCountry()
        };
        CsvWriter<Customer> csvWriter = new CsvWriter<>();
        csvWriter.writeCsv(customers, filePath, HEADER, mapper);
    }
}




