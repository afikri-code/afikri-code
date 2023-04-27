package de.tramotech.restapp.controller;

import de.tramotech.restapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller that handles requests related to transactions.
 * Author: Ahmed Fikri
 */
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadCsvFile(@RequestPart("file") MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<String> lines = new ArrayList<>();
            String line;
            if(((line = reader.readLine()) != null)) {
                //skip first line
            }
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            transactionService.saveAllTransactions(lines);

            // Return a success response
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception ex) {
            // Handle any errors that occur during file processing
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + ex.getMessage());
        }
    }
}
