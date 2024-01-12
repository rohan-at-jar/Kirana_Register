package com.kirana.Kirana_Register.model.reports;

/**
 * Represents a monthly financial report containing total credit, total debit, and net balance.
 */
public class MonthlyReport {
    private double totalCredit;
    private double totalDebit;
    private double netBalance;

    // Constructors, getters, and setters

    /**
     * Default constructor for MonthlyReport.
     */
    public MonthlyReport() {
    }

    /**
     * Parameterized constructor for MonthlyReport.
     *
     * @param totalCredit Total credit for the month.
     * @param totalDebit  Total debit for the month.
     * @param netBalance  Net balance for the month (total credit - total debit).
     */
    public MonthlyReport(double totalCredit, double totalDebit, double netBalance) {
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
        this.netBalance = netBalance;
    }

    /**
     * Get the total credit for the month.
     *
     * @return The total credit.
     */

    public double getTotalCredit() {
        return totalCredit;
    }

    /**
     * Set the total credit for the month.
     *
     * @param totalCredit The total credit to set.
     */
    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    /**
     * Get the total debit for the month.
     *
     * @return The total debit.
     */
    public double getTotalDebit() {
        return totalDebit;
    }

    /**
     * Set the total debit for the month.
     *
     * @param totalDebit The total debit to set.
     */
    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }

    /**
     * Get the net balance for the month.
     *
     * @return The net balance.
     */
    public double getNetBalance() {
        return netBalance;
    }

    /**
     * Set the net balance for the month.
     *
     * @param netBalance The net balance to set.
     */
    public void setNetBalance(double netBalance) {
        this.netBalance = netBalance;
    }
}
