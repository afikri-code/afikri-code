package de.tramotech.restapp.repository;

import de.tramotech.restapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A Spring Data JPA repository interface for managing transaction entities.
 * Author: Ahmed Fikri
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
