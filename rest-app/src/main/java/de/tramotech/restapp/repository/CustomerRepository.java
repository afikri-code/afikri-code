package de.tramotech.restapp.repository;

import de.tramotech.restapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * A Spring Data JPA repository interface for managing customer entities.
 * Author: Ahmed Fikri
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByCustomerNr(String customerNr);
}
