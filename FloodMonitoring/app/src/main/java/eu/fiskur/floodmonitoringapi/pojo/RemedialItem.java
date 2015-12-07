package eu.fiskur.floodmonitoringapi.pojo;

import java.util.List;

import eu.fiskur.floodmonitoringapi.model.FloodWarning;

public class RemedialItem {
    List<FloodWarning> items;
    FloodWarning item;

    public List<FloodWarning> getItems() {
        return items;
    }

    public void setItems(List<FloodWarning> items) {
        this.items = items;
    }

    public FloodWarning getItem() {
        return item;
    }

    public void setItem(FloodWarning item) {
        this.item = item;
    }
}
