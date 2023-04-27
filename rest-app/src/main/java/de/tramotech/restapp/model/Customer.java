package de.tramotech.restapp.model;

import lombok.Data;

import javax.persistence.*;

/**
 * A domain model class that represents a customer.
 * Author: Ahmed Fikri
 */
@Entity
@Data
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "customer_nr", unique = true)
    String customerNr;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
}
