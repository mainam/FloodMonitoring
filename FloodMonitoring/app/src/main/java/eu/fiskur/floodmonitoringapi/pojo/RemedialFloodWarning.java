package eu.fiskur.floodmonitoringapi.pojo;

import java.util.List;

import eu.fiskur.floodmonitoringapi.model.FloodWarning;

public class RemedialFloodWarning {

    List<FloodWarning> floodWarnings;

    public List<FloodWarning> getFloodWarnings() {
        return floodWarnings;
    }

    public void setFloodWarnings(List<FloodWarning> floodWarnings) {
        this.floodWarnings = floodWarnings;
    }
}
