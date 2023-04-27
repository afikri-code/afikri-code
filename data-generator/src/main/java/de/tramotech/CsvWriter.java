package de.tramotech;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import lombok.NonNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

/**

 A generic class for writing a list of objects of type T to a CSV file.
 <p>
 This class provides a method for writing a list of objects of type T to a CSV file. The class supports customizing
 the separator, escape character, quote character, and header row. The class requires a function that maps each object
 of type T to a string array, which represents a row in the CSV file.
 </p>
 <p>
 The class uses OpenCSV library to handle writing CSV files.
 </p>
 @param <T> the type of objects to write to the CSV file

 Author: Ahmed Fikri
 */
public class CsvWriter<T> {

    /**
     * Writes a list of objects of type T to a CSV file at the specified path.
     *
     * @param data the list of objects of type T to write to the CSV file
     * @param filePath the path of the CSV file to write to
     * @param header the header row for the CSV file
     * @param mapper a function that maps an object of type T to an array of strings containing the CSV record data
     */
    public void writeCsv(@NonNull List<T> data, @NonNull String filePath, @NonNull String[] header, @NonNull Function<T, String[]> mapper) {
        try(FileWriter writer = new FileWriter(filePath);
            ICSVWriter csvWriter = new CSVWriterBuilder(writer)
                    .withSeparator(';')
                    .withEscapeChar('\\')
                    .withQuoteChar(ICSVWriter.NO_QUOTE_CHARACTER)
                    .build()){

            // Write header row
            csvWriter.writeNext(header);

            // Map objects of type T to CSV record data and write to file
            data.stream()
                    .map(mapper)
                    .forEach(csvWriter::writeNext);

        } catch (IOException e) {
            throw new CsvWriterException("Error writing customers to CSV file", e);
        }
    }
}

