package com.kirana.Kirana_Register.services.reportgeneratorservices;
import com.kirana.Kirana_Register.entities.Transaction;
import com.kirana.Kirana_Register.model.reports.WeeklyReport;
import com.kirana.Kirana_Register.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * Service class responsible for generating weekly reports based on transactions.
 */
@Service
public class WeeklyReportGenerator {

    /**
     * Repository for interacting with transaction information stored in the database.
     */
    private final TransactionRepository transactionRepository;

    /**
     * Constructor for initializing the WeeklyReportGenerator service with dependencies.
     *
     * @param transactionRepository Repository for interacting with transaction information stored in the database.
     */
    @Autowired
    public WeeklyReportGenerator(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Generates a weekly report based on transactions that occurred up to a given timestamp.
     *
     * @param timestamp The timestamp up to which transactions will be considered for the report.
     * @return A WeeklyReport object containing total credit, total debit, and net balance for the specified week.
     */
    public WeeklyReport generateWeeklyReport(Date timestamp) {
        // Calculate one week before the given timestamp
        Date startDate = new Date(timestamp.getTime() - (7 * 24 * 60 * 60 * 1000));

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
        return new WeeklyReport(totalCredit, totalDebit, netBalance);
    }
}