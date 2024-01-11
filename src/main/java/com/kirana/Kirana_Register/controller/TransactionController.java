package com.kirana.Kirana_Register.controller;

import com.kirana.Kirana_Register.dto.TransactionRequestDTO;
import com.kirana.Kirana_Register.model.reports.DailyReport;
import com.kirana.Kirana_Register.model.reports.MonthlyReport;
import com.kirana.Kirana_Register.model.reports.WeeklyReport;
import com.kirana.Kirana_Register.repository.TransactionRepository;
import com.kirana.Kirana_Register.entities.Transaction;
import com.kirana.Kirana_Register.services.ConvertCurrencyService;
import com.kirana.Kirana_Register.services.reportgeneratorservices.DailyReportGenerator;
import com.kirana.Kirana_Register.services.reportgeneratorservices.MonthlyReportGenerator;
import com.kirana.Kirana_Register.services.reportgeneratorservices.WeeklyReportGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final ConvertCurrencyService currencyconversionservice;
    private final DailyReportGenerator drg;
    private final WeeklyReportGenerator wrg;
    private final MonthlyReportGenerator mrg;

    @Autowired
    TransactionController(TransactionRepository transactionRepository, ConvertCurrencyService currencyconversionservice, DailyReportGenerator drg, WeeklyReportGenerator wrg, MonthlyReportGenerator mrg) {
        this.transactionRepository = transactionRepository;
        this.currencyconversionservice= currencyconversionservice;
        this.drg= drg;
        this.wrg= wrg;
        this.mrg= mrg;

    }


    @GetMapping("/allTransactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

//    @PostMapping("/post")
//    public Transaction addPost(@RequestBody Transaction post) {
//        return transactionRepository.save(post);
//    }


    @PostMapping("/addTransaction")
    public Transaction addTransaction(@RequestBody TransactionRequestDTO requestDTO) {
        // Get the exchange rate for the given currency
//        System.err.println(transaction.getCurrency()); DEAD CODE
//        transaction.setCurrency(transaction.getCurrency()); Dead Code
//        double exchangeRate = currencyconversionservice.getExchangeRate(transaction.getCurrency());
//        double exchangeRate = currencyconversionservice.getExchangeRate("INR"); DEAD CODE

        // Perform currency conversion using the ConvertCurrencyService
//        double convertedAmount = currencyconversionservice.convertAmount(transaction.getAmount(), exchangeRate);
//        transaction.setAmount(convertedAmount);

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
//    Create Endpoints in TransactionController for Reports
@GetMapping("/daily-report")
public DailyReport getDailyReport(@RequestParam("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timestamp) {
    return drg.generateDailyReport(timestamp);
}

    @GetMapping("/weekly-report")
    public WeeklyReport getWeeklyReport(@RequestParam("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timestamp) {
        return wrg.generateWeeklyReport(timestamp);
    }

    @GetMapping("/monthly-report")
    public MonthlyReport getMonthlyReport(@RequestParam("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timestamp) {
        return mrg.generateMonthlyReport(timestamp);
    }
}
