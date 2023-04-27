package de.tramotech;


import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        DataGenerator generator = new DataGenerator("de");
        CustomerCsvWriter csvWriter = new CustomerCsvWriter();
        csvWriter.writeCustomersToCsv(generator.generateCustomers(100000), "/Users/afikri/dev/tmp/customer.csv");
    }
}
