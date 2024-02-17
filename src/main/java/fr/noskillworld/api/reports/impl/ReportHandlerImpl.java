package fr.noskillworld.api.reports.impl;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.entities.NSWPlayer;
import fr.noskillworld.api.reports.Report;
import fr.noskillworld.api.reports.ReportType;
import fr.noskillworld.api.reports.ReportsHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReportHandlerImpl implements ReportsHandler {

    private List<Report> reports;

    public ReportHandlerImpl() {
        NSWAPI.getAPI().getServerHandler().getExecutor().execute(() -> reports = NSWAPI.getAPI().getDatabaseManager().getRequestSender().getReports());
    }

    @Override
    public List<Report> getReports() {
        return reports;
    }

    @Override
    public List<Report> getUnResolvedReports() {
        List<Report> unresolvedReports = new ArrayList<>();

        for (Report report : reports) {
            if (!report.isResolved()) {
                unresolvedReports.add(report);
            }
        }
        return unresolvedReports;
    }

    @Override
    public List<Report> getResolvedReports() {
        List<Report> resolvedReports = new ArrayList<>();

        for (Report report : reports) {
            if (report.isResolved()) {
                resolvedReports.add(report);
            }
        }
        return resolvedReports;
    }

    @Override
    public List<Report> getPlayerReports(NSWPlayer player) {
        List<Report> playerReports = new ArrayList<>();

        for (Report report : reports) {
            if (report.getReportedUuid() == player.getUniqueId()) {
                playerReports.add(report);
            }
        }
        return playerReports;
    }

    @Override
    public List<Report> getPlayerReportsCreated(NSWPlayer player) {
        List<Report> playerReportsCreated = new ArrayList<>();

        for (Report report : reports) {
            if (report.getCreatorUuid() == player.getUniqueId()) {
                playerReportsCreated.add(report);
            }
        }
        return playerReportsCreated;
    }

    @Override
    public Report getReportById(int id) {
        for (Report report : reports) {
            if (report.getId() == id) {
                return report;
            }
        }
        return null;
    }

    @Override
    public void createReport(@NotNull NSWPlayer creator, @NotNull NSWPlayer reported, ReportType type, String reason) {
        //TODO
    }

    @Override
    public void deleteReport(int id) {
        //TODO
    }

    @Override
    public void markReportAsResolved(int id) {
        //TODO
    }

    @Override
    public void markReportAsUnresolved(int id) {
        //TODO
    }
}
