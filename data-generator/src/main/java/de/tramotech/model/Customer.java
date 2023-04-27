package de.tramotech.model;


import lombok.Data;

import java.time.LocalDate;

/**

 Customer class represents a customer entity with various attributes such as id, customer id, first name, last name,
 birthday, email, phone number, address, city, postal code, address country, and place of birth country.
 It is used to store and manage customer data.

 Author: Ahmed Fikri
 */
@Data
public class Customer {
    private String customerNr;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String postalCode;
    private String addressCountry;
    private String placeOfBirthCountry;
}
