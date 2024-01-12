package com.kirana.Kirana_Register.model.reports;

/**
 * Represents a daily financial report containing total credit, total debit, and net balance.
 */
public class DailyReport {
    private double totalCredit;
    private double totalDebit;
    private double netBalance;

    // Constructors, getters, and setters

    /**
     * Default constructor for DailyReport.
     */
    public DailyReport() {
    }

    /**
     * Parameterized constructor for DailyReport.
     *
     * @param totalCredit Total credit for the day.
     * @param totalDebit  Total debit for the day.
     * @param netBalance  Net balance for the day (total credit - total debit).
     */
    public DailyReport(double totalCredit, double totalDebit, double netBalance) {
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
        this.netBalance = netBalance;
    }

    /**
     * Get the total credit for the day.
     *
     * @return The total credit.
     */
    public double getTotalCredit() {
        return totalCredit;
    }

    /**
     * Set the total credit for the day.
     *
     * @param totalCredit The total credit to set.
     */

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    /**
     * Get the total debit for the day.
     *
     * @return The total debit.
     */
    public double getTotalDebit() {
        return totalDebit;
    }

    /**
     * Set the total debit for the day.
     *
     * @param totalDebit The total debit to set.
     */
    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }

    /**
     * Get the net balance for the day.
     *
     * @return The net balance.
     */
    public double getNetBalance() {
        return netBalance;
    }

    /**
     * Set the net balance for the day.
     *
     * @param netBalance The net balance to set.
     */
    public void setNetBalance(double netBalance) {
        this.netBalance = netBalance;
    }
}
