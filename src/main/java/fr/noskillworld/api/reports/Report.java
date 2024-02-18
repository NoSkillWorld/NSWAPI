package fr.noskillworld.api.reports;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Report {

    private final int id;
    private final UUID creatorUuid;
    private final UUID reportedUuid;
    private final ReportType reportType;
    private final String reason;
    private boolean isResolved;
    private final Timestamp timestamp;

    public Report(int id, UUID creatorUuid, UUID reportedUuid, ReportType type, String reason, boolean isResolved, Timestamp timestamp) {
        this.id = id;
        this.creatorUuid = creatorUuid;
        this.reportedUuid = reportedUuid;
        this.reportType = type;
        this.reason = reason;
        this.isResolved = isResolved;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public UUID getCreatorUuid() {
        return creatorUuid;
    }

    public UUID getReportedUuid() {
        return reportedUuid;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public String getReason() {
        return reason;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getReportDate() {
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp);
    }

    public String getReportTime() {
        return new SimpleDateFormat("HH:mm").format(timestamp);
    }

    public void setResolved(boolean isResolved) {
        this.isResolved = isResolved;
    }
}
