package eu.fiskur.floodmonitoringharness;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import eu.fiskur.floodmonitoringapi.model.Station;
import eu.fiskur.floodmonitoringapi.model.WarningItem;

public class StationsAdapter extends BaseAdapter {

    private Context context;
    private List<Station> stations;

    public StationsAdapter(Context context, List<Station> stations){
        this.context = context;
        this.stations = stations;
    }

    @Override
    public int getCount() {
        return stations.size();
    }

    @Override
    public Station getItem(int position) {
        return stations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        StationHolder holder;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.row_warning, parent, false);
            holder = new StationHolder();
            holder.stationLabel = (TextView) row.findViewById(R.id.warning_severity);
            holder.riverTownName = (TextView) row.findViewById(R.id.warning_description);
            holder.catchmentName = (TextView) row.findViewById(R.id.warning_location);
            row.setTag(holder);
        }else{
            holder = (StationHolder)row.getTag();
        }

        Station station = stations.get(position);
        String label = station.getLabel().toString();
        if(isPopulated(label)){
            holder.stationLabel.setText(label);
        }else{
            holder.stationLabel.setText("Unknown Station");
        }

        String river = station.getRiverName();
        String town = station.getTown();

        if(isPopulated(river) && isPopulated(town)){
            holder.riverTownName.setText(river + ", " + town);
            holder.riverTownName.setVisibility(View.VISIBLE);
        }else if(isPopulated(river)){
            holder.riverTownName.setText(river);
            holder.riverTownName.setVisibility(View.VISIBLE);
        }else if(isPopulated(town)){
            holder.riverTownName.setText(town);
            holder.riverTownName.setVisibility(View.VISIBLE);
        }else{
            holder.riverTownName.setVisibility(View.GONE);
        }

        String catchment = station.getCatchmentName();
        if(isPopulated(catchment)){
            holder.catchmentName.setText(catchment);
            holder.catchmentName.setVisibility(View.VISIBLE);
        }else{
            holder.catchmentName.setVisibility(View.GONE);
        }

        return row;
    }

    private boolean isPopulated(String field){
        if(field != null && !field.isEmpty() && !field.toLowerCase().equals("null")){
            return true;
        }else{
            return false;
        }
    }

    private static class StationHolder {
        TextView stationLabel;
        TextView riverTownName;
        TextView catchmentName;
    }
}
