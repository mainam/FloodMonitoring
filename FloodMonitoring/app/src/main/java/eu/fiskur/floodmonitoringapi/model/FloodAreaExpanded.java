package eu.fiskur.floodmonitoringapi.model;

import com.google.gson.annotations.SerializedName;

public class FloodAreaExpanded {
    @SerializedName("@id") String id;
    String county;
    WarningItem currentWarning;
    String description;
    String eaAreaName;
    String eaRegionName;
    //envelope
    String fwdCode;
    String label;
    Float lat;
    @SerializedName("long")Float lon;
    String notation;
    String polygon;
    String quickDialNumber;
    String riverOrSea;
    String[] type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WarningItem getCurrentWarning() {
        return currentWarning;
    }

    public void setCurrentWarning(WarningItem currentWarning) {
        this.currentWarning = currentWarning;
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

    public String getFwdCode() {
        return fwdCode;
    }

    public void setFwdCode(String fwdCode) {
        this.fwdCode = fwdCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public String getQuickDialNumber() {
        return quickDialNumber;
    }

    public void setQuickDialNumber(String quickDialNumber) {
        this.quickDialNumber = quickDialNumber;
    }

    public String getRiverOrSea() {
        return riverOrSea;
    }

    public void setRiverOrSea(String riverOrSea) {
        this.riverOrSea = riverOrSea;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}


/*
{
  "@context": "http://environment.data.gov.uk/flood-monitoring/meta/context.jsonld",
  "meta": {
    "publisher": "Environment Agency",
    "licence": "http://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/",
    "documentation": "http://environment.data.gov.uk/flood-monitoring/doc/reference",
    "version": "0.5",
    "comment": "Status: Beta service",
    "hasFormat": [
      "http://environment.data.gov.uk/flood-monitoring/id/floodAreas/013WAFUI.rdf",
      "http://environment.data.gov.uk/flood-monitoring/id/floodAreas/013WAFUI.ttl"
    ]
  },
  "items": {
    "@id": "http://environment.data.gov.uk/flood-monitoring/id/floodAreas/013WAFUI",
    "county": "Greater Manchester and Derbyshire",
    "currentWarning": {
      "@id": "http://environment.data.gov.uk/flood-monitoring/id/floods/93010",
      "description": "Upper River Irwell catchment with Oldham, Bolton, Rochdale, Haslingden, Ramsbottom and Rawtenstall.",
      "eaAreaName": "South",
      "eaRegionName": "North West",
      "floodArea": "http://environment.data.gov.uk/flood-monitoring/id/floodAreas/013WAFUI",
      "floodAreaID": "013WAFUI",
      "isTidal": false,
      "message": "River levels are falling and the situation in the area is now improving. \nFurther showers are expected during the night tonight. Duty officers are continuing to monitor the situation and further information will follow in due course if required.",
      "severity": "Warning no longer in force",
      "severityLevel": 4,
      "timeMessageChanged": "2015-11-09T20:04:00",
      "timeRaised": "2015-11-09T20:04:00",
      "timeSeverityChanged": "2015-11-09T20:04:00",
      "type": "http://environment.data.gov.uk/flood-monitoring/def/core/FloodAlertOrWarning"
    },
    "description": "The Upper Irwell catchment includes the Rivers Beal, Roch and Croal, Limey Water and their tributaries.  Other locations which may be affected are around Farnworth, Whitefield, Little Lever, Radcliffe, Bury, Heywood, Whitworth and Bacup",
    "eaAreaName": "South",
    "eaRegionName": "North West Region",
    "envelope": {
      "lowerCorner": {
        "x": 363868.309950249,
        "y": 405542.013432836
      },
      "upperCorner": {
        "x": 395054.670149254,
        "y": 428269.708955224
      }
    },
    "fwdCode": "013WAFUI",
    "label": "Upper River Irwell catchment with Oldham, Bolton, Rochdale, Haslingden, Ramsbottom and Rawtenstall.",
    "lat": 53.614765880652136,
    "long": -2.303893316563973,
    "notation": "013WAFUI",
    "polygon": "http://environment.data.gov.uk/flood-monitoring/id/floodAreas/013WAFUI/polygon",
    "quickDialNumber": "143002",
    "riverOrSea": "Upper River Irwel",
    "type": [
      "http://environment.data.gov.uk/flood-monitoring/def/core/FloodAlertArea",
      "http://environment.data.gov.uk/flood-monitoring/def/core/FloodArea"
    ]
  }
}
 */
