package eu.fiskur.floodmonitoringharness;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.fiskur.floodmonitoringapi.FloodApiLogger;
import eu.fiskur.floodmonitoringapi.FloodMonitoring;
import eu.fiskur.floodmonitoringapi.model.Reading;
import eu.fiskur.floodmonitoringapi.model.Readings;
import eu.fiskur.floodmonitoringapi.model.Station;
import eu.fiskur.floodmonitoringapi.model.Stations;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class StationsDemoActivity extends AppCompatActivity {
    final static private String[] LOCATION_PERMISSIONS = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    final static private int PERMISSIONS_CODE = 0;

    @Bind(R.id.chart) LineChartView chart;
    @Bind(R.id.stations_demo_list) ListView stationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stations_demo);

        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);

        Timber.plant(new Timber.DebugTree());

        if (ContextCompat.checkSelfPermission(this, LOCATION_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, LOCATION_PERMISSIONS[1]) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(this, LOCATION_PERMISSIONS, PERMISSIONS_CODE);
        }

        FloodApiLogger.getInstance().setApiLogListener(new FloodApiLogger.ApiLogListener() {
            @Override
            public void apiLog(String message) {
                log(message);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        }
    }

    private void getLocation(){
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        try {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null){
                FloodMonitoring.getInstance().getAreaStations(location.getLatitude(), location.getLongitude(), 10)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(new Observer<Stations>() {
                            @Override
                            public void onCompleted() {
                                Timber.d("Rx onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.d("Rx onError: " + e.toString());
                                Toast.makeText(StationsDemoActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onNext(Stations stations) {
                                Timber.d("Rx onNext");
                                buildList(stations);
                            }
                        });
            }
        }catch(SecurityException se){
            //
        }
    }

    private void buildList(Stations stations){
        if(stations.getItems() == null || stations.getItems().size() == 0){
            //TODO - confirm this is miles and not km?
            Toast.makeText(StationsDemoActivity.this, "No stations found within 10 miles", Toast.LENGTH_LONG).show();
        }else{
            final StationsAdapter stationsAdapter = new StationsAdapter(StationsDemoActivity.this, stations.getItems());
            stationsList.setAdapter(stationsAdapter);

            stationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Station station = stationsAdapter.getItem(position);
                    log("Clicked station: " + station.getLabel());

                    String measureId = station.getMeasures().get(0).getId();

                    FloodMonitoring.getInstance().getReadings(measureId, 100)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(new Observer<Readings>() {
                                @Override
                                public void onCompleted() {
                                    Timber.d("Rx onCompleted");
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Timber.d("Rx onError: " + e.toString());
                                    Toast.makeText(StationsDemoActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onNext(Readings readings) {
                                    Timber.d("Rx onNext");
                                    log("Got " + readings.getItems().size() + " readings");
                                    populateChart(readings.getItems());
                                }
                            });
                }
            });
        }
    }

    private void populateChart(List<Reading> readings){
        if(readings == null || readings.size() == 0){
            chart.setVisibility(View.GONE);
            Toast.makeText(this, "No readings available", Toast.LENGTH_SHORT).show();
            return;
        }
        chart.setVisibility(View.VISIBLE);
        List<PointValue> values = new ArrayList<PointValue>();

        for(int i = 0 ; i < readings.size() ; i++){
            Reading reading = readings.get(i);
            values.add(new PointValue(i, reading.getValue()));
        }

        Line line = new Line(values).setColor(Color.GRAY).setCubic(true);

        line.setFilled(true);
        line.setHasLines(true);
        line.setHasPoints(false);
        line.setHasLabels(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);


        chart.setLineChartData(data);

    }

    private void log(String message){
        Timber.d(message);
    }
}
