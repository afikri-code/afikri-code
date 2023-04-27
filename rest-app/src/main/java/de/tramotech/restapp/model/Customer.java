package de.tramotech.restapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    private String customerNr;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;
}
