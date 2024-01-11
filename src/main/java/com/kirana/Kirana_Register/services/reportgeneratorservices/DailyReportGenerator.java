package com.kirana.Kirana_Register.services.reportgeneratorservices;
import com.kirana.Kirana_Register.entities.Transaction;
import com.kirana.Kirana_Register.model.reports.DailyReport;
import com.kirana.Kirana_Register.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class DailyReportGenerator {

    private final TransactionRepository transactionRepository;

    @Autowired
    public DailyReportGenerator(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public DailyReport generateDailyReport(Date timestamp) {
        // Calculate one day before the given timestamp
        Date startDate = new Date(timestamp.getTime() - (24 * 60 * 60 * 1000));

        // Fetch transactions between startDate and timestamp
//        return transactionRepository.findByTimestampBetween(startDate, timestamp);
        List<Transaction> transactions = transactionRepository.findByTimestampBetween(startDate, timestamp);

        // Initialize totals
        double totalCredit = 0;
        double totalDebit = 0;

        // Calculate totals
        for (Transaction transaction : transactions) {
            if ("credit".equalsIgnoreCase(transaction.getType())) {
                totalCredit += transaction.getAmount();
            } else if ("debit".equalsIgnoreCase(transaction.getType())) {
                totalDebit += transaction.getAmount();
            }
        }

        // Calculate net balance
        double netBalance = totalCredit - totalDebit;

        return new DailyReport(totalCredit, totalDebit, netBalance);
        // Create and return the response as a Map
//        Map<String, Double> dailyReport = new HashMap<>();
//        dailyReport.put("totalCredit", totalCredit);
//        dailyReport.put("totalDebit", totalDebit);
//        dailyReport.put("netBalance", netBalance);
//
//        return dailyReport;
    }
}