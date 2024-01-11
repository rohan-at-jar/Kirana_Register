package com.kirana.Kirana_Register.model.reports;

public class DailyReport {
    private double totalCredit;
    private double totalDebit;
    private double netBalance;

    // Constructors, getters, and setters

    public DailyReport() {
    }

    public DailyReport(double totalCredit, double totalDebit, double netBalance) {
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
        this.netBalance = netBalance;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public double getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(double netBalance) {
        this.netBalance = netBalance;
    }
}
