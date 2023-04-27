package de.tramotech;

import model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class CsvWriterTest {

    private static final String FILE_PATH = "output.csv";
    private static final String INVALID_FILE_PATH = "";
    private static final String[] HEADER = {"id", "customerId", "firstName", "lastName", "birthday", "email", "phoneNumber", "address", "city", "postalCode", "addressCountry", "placeOfBirthCountry"};
    private CsvWriter<Customer> csvWriter;
    Function<Customer, String[]> mapper;
    List<Customer> data;

    @BeforeEach
    void setUp() {
        deleteFileIfExists(FILE_PATH);
        csvWriter = new CsvWriter<>();
        data = new ArrayList<>();
        mapper = customer -> new String[]{customer.getCustomerNr(), customer.getFirstName(), customer.getLastName(), customer.getBirthday().toString(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress(), customer.getCity(), customer.getPostalCode(), customer.getAddressCountry(), customer.getPlaceOfBirthCountry()};
    }

    @Test
    void testWriteCsv() throws IOException {
        // Write data to file
        csvWriter.writeCsv(data, FILE_PATH, HEADER, mapper);

        // Verify that file was created
        File file = new File(FILE_PATH);

        assertTrue(file.exists());
    }

    @Test
    void testWriteCsv_ToExisitingFile() throws IOException {
        // Write data to file
        csvWriter.writeCsv(data, FILE_PATH, HEADER, mapper);

        // Verify that file was created
        File file = new File(FILE_PATH);

        assertTrue(file.exists());

        csvWriter.writeCsv(data, FILE_PATH, HEADER, mapper);

        assertTrue(file.exists());
    }


    @Test
    void testWriteCsv_NullData() {
        assertThrows(NullPointerException.class, () -> csvWriter.writeCsv(null, FILE_PATH, HEADER, mapper));
    }

    @Test
    void testWriteCsv_NullFilePath() {
        assertThrows(NullPointerException.class, () -> csvWriter.writeCsv(data, null, HEADER, mapper));
    }

    @Test
    void testWriteCsv_NullHeader() {
        assertThrows(NullPointerException.class, () -> csvWriter.writeCsv(data, FILE_PATH, null, mapper));
    }

    @Test
    void testWriteCsv_NullMapper() {
        assertThrows(NullPointerException.class, () -> csvWriter.writeCsv(data, FILE_PATH, HEADER, null));
    }

    @Test
    void testWriteCsv_InvalidFilePath() {
        assertThrows(CsvWriterException.class, () -> csvWriter.writeCsv(data, INVALID_FILE_PATH, HEADER, mapper))
                .getMessage().equals("No such file or directory");
    }

    private static void deleteFileIfExists(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully: " + filePath);
            } else {
                System.out.println("Failed to delete file: " + filePath);
            }
        } else {
            System.out.println("File does not exist: " + filePath);
        }
    }


}