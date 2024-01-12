package com.kirana.Kirana_Register.controller;

import com.kirana.Kirana_Register.dto.TransactionRequestDTO;
import com.kirana.Kirana_Register.repository.TransactionRepository;
import com.kirana.Kirana_Register.entities.Transaction;
import com.kirana.Kirana_Register.services.ConvertCurrencyService;
import com.kirana.Kirana_Register.services.reportgeneratorservices.DailyReportGenerator;
import com.kirana.Kirana_Register.services.reportgeneratorservices.MonthlyReportGenerator;
import com.kirana.Kirana_Register.services.reportgeneratorservices.WeeklyReportGenerator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {

    private final  TransactionRepository transactionRepository;
    private final ConvertCurrencyService currencyconversionservice;
    private final DailyReportGenerator drg;
    private final WeeklyReportGenerator wrg;
    private final MonthlyReportGenerator mrg;

    TransactionController(TransactionRepository transactionRepository, ConvertCurrencyService currencyconversionservice, DailyReportGenerator drg, WeeklyReportGenerator wrg, MonthlyReportGenerator mrg) {
        this.transactionRepository = transactionRepository;
        this.currencyconversionservice= currencyconversionservice;
        this.drg= drg;
        this.wrg= wrg;
        this.mrg= mrg;

    }


    @GetMapping("/allTransactions")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @PostMapping("/addTransaction")
    public Transaction addTransaction(@RequestBody TransactionRequestDTO requestDTO) {

        Transaction transaction = new Transaction();
        transaction.setFrom(requestDTO.getFrom());
        transaction.setTo(requestDTO.getTo());
        transaction.setCurrency(requestDTO.getCurrency());
        transaction.setAmount(requestDTO.getAmount());
        transaction.setType(requestDTO.getType());

        // Capture the current timestamp
        transaction.setTimestamp(new Date());


        // Get the exchange rate for the given currency
        double exchangeRate = currencyconversionservice.getExchangeRate(requestDTO.getCurrency());

        // Perform currency conversion and update the amount
        double convertedAmount = currencyconversionservice.convertAmount(requestDTO.getAmount(), exchangeRate);
        transaction.setAmount(convertedAmount);

        // Save the transaction
        return transactionRepository.save(transaction);
    }
}
