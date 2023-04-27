package de.tramotech;

import model.Transaction;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

public class TransactionCsvWriter {

    private final String[] HEADER = {"transactionNr", "customerNr", "credit_flg", "amount", "date"};

    /**
     * Writes a list of transactions to a CSV file with the given file path.
     * Each transaction is mapped to a String[] using the provided mapper function.
     * The CSV file will contain a header row with the column names defined in the HEADER constant.
     *
     * @param transactions the list of transactions to write to the CSV file
     * @param filePath the file path of the CSV file to write
     * @throws IOException if an I/O error occurs while writing the CSV file
     */
    public void writeTransactionsToCsv(List<Transaction> transactions, String filePath) throws IOException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Function<Transaction, String[]> mapper = transaction -> new String[]{
                transaction.getTransactionNr(),
                transaction.getCustomerNr(),
                transaction.isCredit()?"1":"0",
                String.format("%.2f", transaction.getAmount()),
                transaction.getDate().format(dateFormatter),
        };
        CsvWriter<Transaction> csvWriter = new CsvWriter<>();
        csvWriter.writeCsv(transactions, filePath, HEADER, mapper);
    }

}

