package de.tramotech.restapp.controller;

import de.tramotech.restapp.dto.CustomerDto;
import de.tramotech.restapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller that handles requests related to customers.
 * Author: Ahmed Fikri
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
     @Autowired
    CustomerService customerService;

    // Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerDto);
    }

    // Get customer by customer number
    @GetMapping("/customerNr/{customerNr}")
    public ResponseEntity<CustomerDto> getCustomerByCustomerNr(@PathVariable("customerNr") String customerNr) {
        CustomerDto customerDto = customerService.getCustomerByCustomerNr(customerNr);
        return ResponseEntity.ok(customerDto);
    }

    @PostMapping()
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomerDto = customerService.saveCustomer(customerDto);
        return ResponseEntity.ok(savedCustomerDto);
    }

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
            customerService.saveAllCustomers(lines);

            // Return a success response
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception ex) {
            // Handle any errors that occur during file processing
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + ex.getMessage());
        }
    }


    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/customerNr/{customerNr}")
    public ResponseEntity<Void> deleteCustomerByCustomerNr(@PathVariable("customerNr") String customerNr) {
        customerService.deleteCustomerByCustomerNr(customerNr);
        return ResponseEntity.noContent().build();
    }
}
