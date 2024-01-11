package com.kirana.Kirana_Register.repository;

import com.kirana.Kirana_Register.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByTimestampBetween(Date startDate, Date timestamp);

}
