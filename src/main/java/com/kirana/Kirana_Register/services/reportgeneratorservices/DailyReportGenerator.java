package com.kirana.Kirana_Register.services.reportgeneratorservices;
import com.kirana.Kirana_Register.entities.Transaction;
import com.kirana.Kirana_Register.model.reports.DailyReport;
import com.kirana.Kirana_Register.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * Service class responsible for generating daily reports based on transactions.
 */
@Service
public class DailyReportGenerator {

    /**
     * Repository for interacting with transaction information stored in the database.
     */
    private final TransactionRepository transactionRepository;

    /**
     * Constructor for initializing the DailyReportGenerator service with dependencies.
     *
     * @param transactionRepository Repository for interacting with transaction information stored in the database.
     */
    @Autowired
    public DailyReportGenerator(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Generates a daily report based on transactions that occurred up to a given timestamp.
     *
     * @param timestamp The timestamp up to which transactions will be considered for the report.
     * @return A DailyReport object containing total credit, total debit, and net balance for the specified day.
     */
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
    }
}