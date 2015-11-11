package eu.fiskur.floodmonitoringharness;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import eu.fiskur.floodmonitoringapi.model.WarningItem;

public class WarningsListAdapter extends BaseAdapter {

    private Context context;
    private List<WarningItem> warnings;

    public WarningsListAdapter(Context context, List<WarningItem> warnings){
        this.context = context;
        this.warnings = warnings;
    }

    @Override
    public int getCount() {
        return warnings.size();
    }

    @Override
    public WarningItem getItem(int position) {
        return warnings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WarningHolder holder;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.row_warning, parent, false);
            holder = new WarningHolder();
            holder.warningSeverity = (TextView) row.findViewById(R.id.warning_severity);
            holder.warningDescription = (TextView) row.findViewById(R.id.warning_description);
            holder.warningLocation = (TextView) row.findViewById(R.id.warning_location);
            row.setTag(holder);
        }else{
            holder = (WarningHolder)row.getTag();
        }

        WarningItem warning = warnings.get(position);
        holder.warningSeverity.setText(warning.getSeverity());
        holder.warningDescription.setText(warning.getDescription());
        holder.warningLocation.setText(warning.getFloodArea().getRiverOrSea() + ", " + warning.getFloodArea().getCounty());

        return row;
    }

    private static class WarningHolder {
        TextView warningSeverity;
        TextView warningDescription;
        TextView warningLocation;
    }
}
