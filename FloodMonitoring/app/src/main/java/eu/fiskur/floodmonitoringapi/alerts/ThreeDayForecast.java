package eu.fiskur.floodmonitoringapi.alerts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import eu.fiskur.floodmonitoringapi.model.ForecastRisk;

public class ThreeDayForecast {
    @SerializedName("@id") String id;
    String day1image;
    String day2image;
    String day3image;
    List<ForecastRisk> forecastRisk;
    String forecastSummary;
    String forecastSummaryWelsh;
    String issueDatetime;
    String label;
    String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay1image() {
        return day1image;
    }

    public void setDay1image(String day1image) {
        this.day1image = day1image;
    }

    public String getDay2image() {
        return day2image;
    }

    public void setDay2image(String day2image) {
        this.day2image = day2image;
    }

    public String getDay3image() {
        return day3image;
    }

    public void setDay3image(String day3image) {
        this.day3image = day3image;
    }

    public String getForecastSummary() {
        return forecastSummary;
    }

    public void setForecastSummary(String forecastSummary) {
        this.forecastSummary = forecastSummary;
    }

    public String getForecastSummaryWelsh() {
        return forecastSummaryWelsh;
    }

    public void setForecastSummaryWelsh(String forecastSummaryWelsh) {
        this.forecastSummaryWelsh = forecastSummaryWelsh;
    }

    public String getIssueDatetime() {
        return issueDatetime;
    }

    public void setIssueDatetime(String issueDatetime) {
        this.issueDatetime = issueDatetime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ForecastRisk> getForecastRisk() {
        return forecastRisk;
    }

    public void setForecastRisk(List<ForecastRisk> forecastRisk) {
        this.forecastRisk = forecastRisk;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if(id != null){
            sb.append("@id: " + id + "\n");
        }
        if(forecastSummary != null){
            sb.append("forecastSummary: " + forecastSummary + "\n");
        }
        if(issueDatetime != null){
            sb.append("issueDatetime: " + issueDatetime + "\n");
        }
        if(type != null){
            sb.append("type: " + type + "\n");
        }
        return sb.toString();
    }
}
