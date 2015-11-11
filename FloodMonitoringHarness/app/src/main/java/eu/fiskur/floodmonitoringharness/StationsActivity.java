package eu.fiskur.floodmonitoringharness;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.fiskur.floodmonitoringapi.FloodApiLogger;
import eu.fiskur.floodmonitoringapi.FloodMonitoring;
import eu.fiskur.floodmonitoringapi.FloodUtils;
import eu.fiskur.floodmonitoringapi.model.Meta;
import eu.fiskur.floodmonitoringapi.model.Station;
import eu.fiskur.floodmonitoringapi.model.Stations;
import eu.fiskur.floodmonitoringapi.model.WarningItem;
import eu.fiskur.floodmonitoringapi.model.Warnings;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class StationsActivity extends AppCompatActivity {

    final static private String[] LOCATION_PERMISSIONS = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    final static private int PERMISSIONS_CODE = 0;


    @Bind(R.id.scroll_layout) ScrollView scrollLayout;
    @Bind(R.id.api_layout) LinearLayout apiLayout;

    @Bind(R.id.get_all_stations_button) Button getAllButton;

    @Bind(R.id.county_spinner) Spinner countySpinner;
    @Bind(R.id.get_stations_county_button) Button countyButton;

    @Bind(R.id.stations_location_latitude_edit) EditText latitudeEdit;
    @Bind(R.id.stations_location_longitude_edit) EditText longitudeEdit;
    @Bind(R.id.stations_location_distance_edit) EditText distanceEdit;
    @Bind(R.id.get_stations_location_button) Button locationButton;

    @Bind(R.id.log_layout) LinearLayout logLayout;
    @Bind(R.id.log_view) EditText logView;
    @Bind(R.id.log_clear_button) Button clearLogButton;
    @Bind(R.id.log_close_button) Button closeLogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stations);

        ButterKnife.bind(this);

        Timber.plant(new Timber.DebugTree());

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, FloodUtils.counties);
        countySpinner.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(this, LOCATION_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, LOCATION_PERMISSIONS[1]) == PackageManager.PERMISSION_GRANTED    ) {
            log("Location permissions have already been granted. Displaying contact details.");
            getLocation();
        } else {
            log("Location permissions has NOT been granted. Requesting permission.");
            ActivityCompat.requestPermissions(this, LOCATION_PERMISSIONS, PERMISSIONS_CODE);
        }

        FloodUtils.hideKeyboard(StationsActivity.this, latitudeEdit);
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
                latitudeEdit.setText("" + location.getLatitude());
                longitudeEdit.setText("" + location.getLongitude());
            }
        }catch(SecurityException se){

        }
    }

    private boolean hasLocationPermission(){
        int res = checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        boolean fineLocGranted = (res == PackageManager.PERMISSION_GRANTED);

        res = checkCallingOrSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        boolean coarseLocGranted = (res == PackageManager.PERMISSION_GRANTED);

        if(fineLocGranted && coarseLocGranted){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Too much json to display so don't log api response:
        FloodApiLogger.getInstance().setApiLogListener(null);
    }

    @Override
    public void onBackPressed() {
        if(logLayout.getVisibility() == View.VISIBLE){
            apiLayout.setVisibility(View.VISIBLE);
            logLayout.setVisibility(View.GONE);
        }else {
            super.onBackPressed();
        }
    }

    @OnClick({ R.id.log_clear_button,
            R.id.log_close_button,
            R.id.get_all_stations_button,
            R.id.get_stations_county_button,
            R.id.get_stations_location_button})
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.log_clear_button:
                logView.setText("");
                apiLayout.setVisibility(View.VISIBLE);
                logLayout.setVisibility(View.GONE);
                break;
            case R.id.log_close_button:
                apiLayout.setVisibility(View.VISIBLE);
                logLayout.setVisibility(View.GONE);
                break;
            case R.id.get_all_stations_button:
                apiLayout.setVisibility(View.GONE);
                logLayout.setVisibility(View.VISIBLE);
                FloodMonitoring.getInstance().getAllStations()
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
                                log("onError: " + e.toString());
                            }

                            @Override
                            public void onNext(Stations stations) {
                                Timber.d("Rx onNext");
                                outputStations(stations);
                            }
                        });
                break;
            case R.id.get_stations_county_button:
                apiLayout.setVisibility(View.GONE);
                logLayout.setVisibility(View.VISIBLE);

                String county = countySpinner.getSelectedItem().toString();

                FloodMonitoring.getInstance().getCountyStations(county)
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
                                log("onError: " + e.toString());
                            }

                            @Override
                            public void onNext(Stations stations) {
                                Timber.d("Rx onNext");
                                outputStations(stations);
                            }
                        });

                break;
            case R.id.get_stations_location_button:
                apiLayout.setVisibility(View.GONE);
                logLayout.setVisibility(View.VISIBLE);

                String lat =  latitudeEdit.getText().toString();
                String lon =  longitudeEdit.getText().toString();
                String distance =  distanceEdit.getText().toString();

                FloodMonitoring.getInstance().getAreaStations(Double.parseDouble(lat), Double.parseDouble(lon), Integer.parseInt(distance))
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
                                log("onError: " + e.toString());
                            }

                            @Override
                            public void onNext(Stations stations) {
                                Timber.d("Rx onNext");
                                outputStations(stations);
                            }
                        });


                break;

        }
    }

    private void outputStations(Stations stations){

        if (stations == null) {
            log("No Warnings object");
            return;
        }

        Meta meta = stations.getMeta();
        if (meta != null) {
            log("Publisher: " + meta.getPublisher());
            log("Licence: " + meta.getLicence());
            log("Documentation: " + meta.getDocumentation());
            log("Version: " + meta.getVersion());
            log("Comment: " + meta.getComment());
            log(Utils.DIV);
        }

        List<Station> items = stations.getItems();

        log("Displaying first 20 entries only.");

        int count = (items.size() > 20) ? 20 : items.size();
        for(int i = 0 ; i < count ; i++){
            Station station = items.get(i);
            log(station.toString());
            log(Utils.DIV);
        }

        //Use county Avon - tests RemedialStringType for inconsistent field that may be string or string[]
        /*
        for(Station station : items){
            if(station.getStationReference().equals("E40411")){
                log(Utils.DIV);
                log("Station with array as name: ");
                log(station.toString());
            }
        }
        */
    }

    private void log(String log){
        Timber.d(log);
        logView.append(log + "\n");
        Utils.clipOutput(logView, 350);
    }
}
