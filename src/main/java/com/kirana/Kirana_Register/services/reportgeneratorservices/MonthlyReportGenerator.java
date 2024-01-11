package com.kirana.Kirana_Register.services.reportgeneratorservices;
import com.kirana.Kirana_Register.entities.Transaction;
import com.kirana.Kirana_Register.model.reports.MonthlyReport;
import com.kirana.Kirana_Register.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class MonthlyReportGenerator {

    private final TransactionRepository transactionRepository;

    @Autowired
    public MonthlyReportGenerator(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public MonthlyReport generateMonthlyReport(Date timestamp) {
        // Calculate one month before the given timestamp
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(Calendar.MONTH, -1);
        Date startDate = calendar.getTime();

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
        return new MonthlyReport(totalCredit, totalDebit, netBalance);
        // Create and return the response as a Map
//        Map<String, Double> monthlyReport = new HashMap<>();
//        monthlyReport.put("totalCredit", totalCredit);
//        monthlyReport.put("totalDebit", totalDebit);
//        monthlyReport.put("netBalance", netBalance);
//
//        return monthlyReport;

    }
}