package com.kirana.Kirana_Register.controller;

import com.kirana.Kirana_Register.model.reports.DailyReport;
import com.kirana.Kirana_Register.model.reports.MonthlyReport;
import com.kirana.Kirana_Register.model.reports.WeeklyReport;
import com.kirana.Kirana_Register.services.reportgeneratorservices.DailyReportGenerator;
import com.kirana.Kirana_Register.services.reportgeneratorservices.MonthlyReportGenerator;
import com.kirana.Kirana_Register.services.reportgeneratorservices.WeeklyReportGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ReportController {
    private final DailyReportGenerator drg;
    private final WeeklyReportGenerator wrg;
    private final MonthlyReportGenerator mrg;


    public ReportController(DailyReportGenerator drg, WeeklyReportGenerator wrg, MonthlyReportGenerator mrg) {
        this.drg = drg;
        this.wrg = wrg;
        this.mrg = mrg;
    }
    @GetMapping("/daily-report")
    @PreAuthorize("hasAuthority('ADMIN')")
    public DailyReport getDailyReport(@RequestParam("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timestamp) {
        return drg.generateDailyReport(timestamp);
    }

    @GetMapping("/weekly-report")
    @PreAuthorize("hasAuthority('ADMIN')")
    public WeeklyReport getWeeklyReport(@RequestParam("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timestamp) {
        return wrg.generateWeeklyReport(timestamp);
    }

    @GetMapping("/monthly-report")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MonthlyReport getMonthlyReport(@RequestParam("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date timestamp) {
        return mrg.generateMonthlyReport(timestamp);
    }
}
