package com.kirana.Kirana_Register.repository;

import com.kirana.Kirana_Register.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for managing transactions in MongoDB.
 */
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    /**
     * Retrieve a list of transactions within a specified date range.
     */
    List<Transaction> findByTimestampBetween(Date startDate, Date timestamp);

}
