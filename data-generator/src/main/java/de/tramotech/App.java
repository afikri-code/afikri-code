package de.tramotech;


import java.io.IOException;

public class App
{
    static void generateCustomerData() throws IOException {
        DataGenerator generator = new DataGenerator("de");
        CustomerCsvWriter csvWriter = new CustomerCsvWriter();
        csvWriter.writeCustomersToCsv(generator.generateCustomers(100), "/Users/afikri/dev/tmp/customer.csv");
    }

    static void generateTransactionData() throws IOException {
        DataGenerator generator = new DataGenerator("de");
        TransactionCsvWriter csvWriter = new TransactionCsvWriter();
        csvWriter.writeTransactionsToCsv(generator.generateTransactions(100, 5,1000), "/Users/afikri/dev/tmp/transaction.csv");
    }
    public static void main( String[] args ) throws IOException {
        generateCustomerData();
        generateTransactionData();
    }
}
