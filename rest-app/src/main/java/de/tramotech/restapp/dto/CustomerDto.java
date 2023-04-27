package de.tramotech.restapp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Data Transfer Object (DTO) class for representing customer data.
 * Author: Ahmed Fikri
 */
@Data
public class CustomerDto {
    Long id;
    @NotEmpty(message = "customer nummer is mandatory")
    String customerNr;
    @NotEmpty(message = "first name is mandatory")
    String firstName;
    @NotEmpty(message = "last name is mandatory")
    String lastName;
    @Email(message = "invalid email address")
    String email;
    @NotEmpty(message = "phone number is mandatory")
    String  phoneNumber;
}
