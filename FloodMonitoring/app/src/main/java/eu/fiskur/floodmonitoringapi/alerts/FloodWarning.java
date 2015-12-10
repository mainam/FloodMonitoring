package eu.fiskur.floodmonitoringapi.alerts;

import android.support.annotation.NonNull;


import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

import eu.fiskur.floodmonitoringapi.model.FloodAreaExpanded;

public class FloodWarning implements Comparable<FloodWarning>{
    @SerializedName("@id") String id;
    String description;
    String eaAreaName;
    String eaRegionName;
    FloodAreaExpanded floodArea;
    String floodAreaID;
    Boolean isTidal;
    String lcounty;
    String message;
    String severity;
    Integer severityLevel;
    String timeMessageChanged;
    String timeRaised;
    String timeSeverityChanged;
    boolean isHeader = false;
    String headerLabel;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEaAreaName() {
        return eaAreaName;
    }

    public void setEaAreaName(String eaAreaName) {
        this.eaAreaName = eaAreaName;
    }

    public String getEaRegionName() {
        return eaRegionName;
    }

    public void setEaRegionName(String eaRegionName) {
        this.eaRegionName = eaRegionName;
    }

    public FloodAreaExpanded getFloodArea() {
        return floodArea;
    }

    public void setFloodArea(FloodAreaExpanded floodArea) {
        this.floodArea = floodArea;
    }

    public String getFloodAreaID() {
        return floodAreaID;
    }

    public void setFloodAreaID(String floodAreaID) {
        this.floodAreaID = floodAreaID;
    }

    public Boolean getIsTidal() {
        return isTidal;
    }

    public void setIsTidal(Boolean isTidal) {
        this.isTidal = isTidal;
    }

    public String getLcounty() {
        return lcounty;
    }

    public void setLcounty(String lcounty) {
        this.lcounty = lcounty;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Integer getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(Integer severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getTimeMessageChanged() {
        return timeMessageChanged;
    }

    public void setTimeMessageChanged(String timeMessageChanged) {
        this.timeMessageChanged = timeMessageChanged;
    }

    public String getTimeRaised() {
        return timeRaised;
    }

    public void setTimeRaised(String timeRaised) {
        this.timeRaised = timeRaised;
    }

    public String getTimeSeverityChanged() {
        return timeSeverityChanged;
    }

    public void setTimeSeverityChanged(String timeSeverityChanged) {
        this.timeSeverityChanged = timeSeverityChanged;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setIsHeader(boolean isHeader, String headerLabel) {
        this.isHeader = isHeader;
        this.headerLabel = headerLabel;
    }

    public String getHeaderLabel() {
        return headerLabel;
    }

    public void setHeaderLabel(String headerLabel) {
        this.headerLabel = headerLabel;
    }

    @Override
    public int compareTo(@NonNull FloodWarning another) {
        return severityLevel - another.getSeverityLevel() ;
    }

    public static Comparator<FloodWarning> WarningItemComparator = new Comparator<FloodWarning>() {

        public int compare(FloodWarning warning1, FloodWarning warning2) {
            return warning1.compareTo(warning2);
        }

    };

}
