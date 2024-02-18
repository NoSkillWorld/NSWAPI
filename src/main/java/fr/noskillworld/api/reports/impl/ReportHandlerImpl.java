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

    @Override
    public List<Report> getReports() {
        return NSWAPI.getAPI().getDatabaseManager().getRequestSender().getReports();
    }

    @Override
    public List<Report> getUnResolvedReports() {
        List<Report> unresolvedReports = new ArrayList<>();
        List<Report> reports = getReports();

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
        List<Report> reports = getReports();

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
        List<Report> reports = getReports();

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
        List<Report> reports = getReports();

        for (Report report : reports) {
            if (report.getCreatorUuid() == player.getUniqueId()) {
                playerReportsCreated.add(report);
            }
        }
        return playerReportsCreated;
    }

    @Override
    public Report getReportById(int id) {
        List<Report> reports = getReports();

        for (Report report : reports) {
            if (report.getId() == id) {
                return report;
            }
        }
        return null;
    }

    @Override
    public void createReport(@NotNull NSWPlayer creator, @NotNull NSWPlayer reported, ReportType type, String reason) {
        NSWAPI.getAPI().getServerHandler().getExecutor().execute(() -> NSWAPI.getAPI().getDatabaseManager().getRequestSender().createReport(creator, reported, type, reason));
    }

    @Override
    public void deleteReport(int id) {
        NSWAPI.getAPI().getServerHandler().getExecutor().execute(() -> NSWAPI.getAPI().getDatabaseManager().getRequestSender().deleteReport(id));
    }

    @Override
    public void markReportAsResolved(int id) {
        NSWAPI.getAPI().getServerHandler().getExecutor().execute(() -> NSWAPI.getAPI().getDatabaseManager().getRequestSender().markReportAsResolved(id));
    }

    @Override
    public void markReportAsUnresolved(int id) {
        NSWAPI.getAPI().getServerHandler().getExecutor().execute(() -> NSWAPI.getAPI().getDatabaseManager().getRequestSender().markReportAsUnresolved(id));
    }
}
