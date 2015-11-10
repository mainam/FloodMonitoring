package eu.fiskur.floodmonitoringapi.model;

import com.google.gson.annotations.SerializedName;

public class FloodArea {
    @SerializedName("@id") String id;
    String county;
    String notation;
    String polygon;
    String riverOrSea;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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

    public String getRiverOrSea() {
        return riverOrSea;
    }

    public void setRiverOrSea(String riverOrSea) {
        this.riverOrSea = riverOrSea;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        if(id != null){
            sb.append("@id: " + id + "\n");
        }
        if(county != null){
            sb.append("county: " + county + "\n");
        }
        if(notation != null){
            sb.append("notation: " + notation + "\n");
        }
        if(polygon != null){
            sb.append("polygon: " + polygon + "\n");
        }
        if(riverOrSea != null){
            sb.append("riverOrSea: " + riverOrSea + "\n");
        }

        return sb.toString();
    }
}
