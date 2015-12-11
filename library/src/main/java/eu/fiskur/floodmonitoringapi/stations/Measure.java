package eu.fiskur.floodmonitoringapi.stations;

import com.google.gson.annotations.SerializedName;

public class Measure {
    @SerializedName("@id") String id;
    String label;
    Reading latestReading;
    String notation;
    String parameter;
    String parameterName;
    Float period;
    String qualifier;
    String station;
    String stationReference;
    String[] type;
    String unitName;
    String valueType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Float getPeriod() {
        return period;
    }

    public void setPeriod(Float period) {
        this.period = period;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public Reading getLatestReading() {
        return latestReading;
    }

    public void setLatestReading(Reading latestReading) {
        this.latestReading = latestReading;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStationReference() {
        return stationReference;
    }

    public void setStationReference(String stationReference) {
        this.stationReference = stationReference;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (id != null) {
            sb.append("@id: " + id + "\n");
        }
        if (parameter != null) {
            sb.append("parameter: " + parameter + "\n");
        }
        if (parameterName != null) {
            sb.append("parameterName: " + parameterName + "\n");
        }
        if (period != null) {
            sb.append("period: " + period + "\n");
        }
        if (qualifier != null) {
            sb.append("qualifier: " + qualifier + "\n");
        }
        if (unitName != null) {
            sb.append("unitName: " + unitName + "\n");
        }

        return sb.toString();
    }

    /*
    Latest reading - sometimes a string sometimes:
    "latestReading": {
        "@id": "http://environment.data.gov.uk/flood-monitoring/data/readings/720215-level-stage-i-15_min-m/2015-11-20T04-30-00Z",
        "date": "2015-11-20",
        "dateTime": "2015-11-20T04:30:00Z",
        "measure": "http://environment.data.gov.uk/flood-monitoring/id/measures/720215-level-stage-i-15_min-m",
        "value": 0.455
      },

      other times:

       "latestReading": "http://environment.data.gov.uk/flood-monitoring/data/readings/690138-level-stage-i-15_min----/2015-07-02T10-15-00Z",

     */
}
