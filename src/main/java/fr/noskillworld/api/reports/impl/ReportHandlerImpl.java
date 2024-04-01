package fr.noskillworld.api.reports.impl;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.entities.NSWPlayer;
import fr.noskillworld.api.reports.Report;
import fr.noskillworld.api.reports.ReportSortType;
import fr.noskillworld.api.reports.ReportType;
import fr.noskillworld.api.reports.ReportsHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReportHandlerImpl implements ReportsHandler {

    private final NSWAPI nswapi;

    public ReportHandlerImpl(NSWAPI api) {
        this.nswapi = api;
    }

    @Override
    public List<Report> getReports() {
        return nswapi.getDatabaseManager().getRequestSender().getReports(ReportSortType.DATE_DESC);
    }

    @Override
    public List<Report> getReportsByName() {
        return nswapi.getDatabaseManager().getRequestSender().getReports(ReportSortType.PLAYER_ASC);
    }

    @Override
    public List<Report> getReportsByNameDesc() {
        return nswapi.getDatabaseManager().getRequestSender().getReports(ReportSortType.PLAYER_DESC);
    }

    @Override
    public List<Report> getReportsByDate() {
        return nswapi.getDatabaseManager().getRequestSender().getReports(ReportSortType.DATE_ASC);
    }

    @Override
    public List<Report> getReportsByDateDesc() {
        return nswapi.getDatabaseManager().getRequestSender().getReports(ReportSortType.DATE_DESC);
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
        nswapi.getServerHandler().getExecutor().execute(() -> nswapi.getDatabaseManager().getRequestSender().createReport(creator, reported, type, reason));
    }

    @Override
    public void deleteReport(int id) {
        nswapi.getServerHandler().getExecutor().execute(() -> nswapi.getDatabaseManager().getRequestSender().deleteReport(id));
    }

    @Override
    public void markReportAsResolved(int id) {
        nswapi.getServerHandler().getExecutor().execute(() -> nswapi.getDatabaseManager().getRequestSender().markReportAsResolved(id));
    }

    @Override
    public void markReportAsUnresolved(int id) {
        nswapi.getServerHandler().getExecutor().execute(() -> nswapi.getDatabaseManager().getRequestSender().markReportAsUnresolved(id));
    }
}
