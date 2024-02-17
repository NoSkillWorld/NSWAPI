package fr.noskillworld.api.reports;

import fr.noskillworld.api.entities.NSWPlayer;

import java.util.List;

public interface ReportsHandler {

    /**
     * Get all the reports, resolved or not
     *
     * @return a list of reports
     */
    List<Report> getReports();

    /**
     * Get a list of unresolved reports
     *
     * @return a list of reports
     */
    List<Report> getUnResolvedReports();

    /**
     * Get a list of resolved reports
     *
     * @return a list of reports
     */
    List<Report> getResolvedReports();

    /**
     * Get the reports that the player has against him
     *
     * @param player the player to check the reports
     * @return a list of reports
     */
    List<Report> getPlayerReports(NSWPlayer player);

    /**
     * Get the reports that the player has created
     *
     * @param player the player to check the reports
     * @return a list of reports
     */
    List<Report> getPlayerReportsCreated(NSWPlayer player);

    /**
     * Get a report by its unique identifier
     *
     * @param id the unique id of the report
     * @return the report
     */
    Report getReportById(int id);

    /**
     * Creates a new report
     *
     * @param creator  the creator of the report
     * @param reported the player to be reported
     * @param type     the type of the report
     * @param reason   the reason of the report
     */
    void createReport(NSWPlayer creator, NSWPlayer reported, ReportType type, String reason);

    /**
     * Deletes a specific report
     *
     * @param id the unique id of the report
     */
    void deleteReport(int id);

    /**
     * Mark a specific report as resolved
     *
     * @param id the unique id of the report
     */
    void markReportAsResolved(int id);

    /**
     * Mark a specific report as unresolved
     *
     * @param id the unique id of the report
     */
    void markReportAsUnresolved(int id);
}
