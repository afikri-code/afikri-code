package de.tramotech;

/**
 * Exception thrown when an error occurs during CSV writing.
 * Author: Ahmed Fikri
 */
public class CsvWriterException extends RuntimeException {

    /**
     * Constructs a new CsvWriterException with the specified message.
     *
     * @param message the message to associate with this exception
     */
    public CsvWriterException(String message) {
        super(message);
    }

    /**
     * Constructs a new CsvWriterException with the specified message and cause.
     *
     * @param message the message to associate with this exception
     * @param cause   the cause of this exception
     */
    public CsvWriterException(String message, Throwable cause) {
        super(message, cause);
    }
}

