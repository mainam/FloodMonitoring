package eu.fiskur.floodmonitoringharness;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import eu.fiskur.floodmonitoringapi.model.WarningItem;
import eu.fiskur.floodmonitoringapi.model.Warnings;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class AlertsActivity extends AppCompatActivity {
    final static private String[] LOCATION_PERMISSIONS = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    final static private int PERMISSIONS_CODE = 0;
    final static private String DIV = "-----------------------------------------\n";

    @Bind(R.id.scroll_layout) ScrollView scrollLayout;
    @Bind(R.id.api_layout) LinearLayout apiLayout;

    @Bind(R.id.min_severity_edit) EditText minSeverityEdit;
    @Bind(R.id.get_min_severity_button) Button minSeverityButton;

    @Bind(R.id.county_spinner) Spinner countySpinner;
    @Bind(R.id.get_county_button) Button countyButton;

    @Bind(R.id.location_latitude_edit) EditText latitudeEdit;
    @Bind(R.id.location_longitude_edit) EditText longitudeEdit;
    @Bind(R.id.location_distance_edit) EditText distanceEdit;
    @Bind(R.id.get_location_button) Button locationButton;

    @Bind(R.id.log_layout) LinearLayout logLayout;
    @Bind(R.id.log_view) EditText logView;
    @Bind(R.id.log_clear_button) Button clearLogButton;
    @Bind(R.id.log_close_button) Button closeLogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, FloodUtils.counties);
        countySpinner.setAdapter(adapter);

        Timber.plant(new Timber.DebugTree());

        FloodUtils.hideKeyboard(AlertsActivity.this, minSeverityEdit);

        if (ContextCompat.checkSelfPermission(this, LOCATION_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, LOCATION_PERMISSIONS[1]) == PackageManager.PERMISSION_GRANTED    ) {
            log("Location permissions have already been granted. Displaying contact details.");
            getLocation();
        } else {
            log("Location permissions has NOT been granted. Requesting permission.");
            ActivityCompat.requestPermissions(this, LOCATION_PERMISSIONS, PERMISSIONS_CODE);
        }

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

        FloodApiLogger.getInstance().setApiLogListener(new FloodApiLogger.ApiLogListener() {
            @Override
            public void apiLog(final String message) {
                Log.d("", message);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        logView.append(message);
                        scrollLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                scrollLayout.fullScroll(View.FOCUS_DOWN);
                            }
                        });
                    }
                });
            }
        });
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
            R.id.get_all_button,
            R.id.get_min_severity_button,
            R.id.get_county_button,
            R.id.get_location_button})
    public void onClick(View view) {
        FloodUtils.hideKeyboard(AlertsActivity.this, minSeverityEdit);
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
            case R.id.get_all_button:
                apiLayout.setVisibility(View.GONE);
                logLayout.setVisibility(View.VISIBLE);
                FloodMonitoring.getInstance().getAllWarnings()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Warnings>() {
                            @Override
                            public void onCompleted() {
                                Timber.d("Rx onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.d("Rx onError: " + e.toString());
                            }

                            @Override
                            public void onNext(Warnings warnings) {
                                Timber.d("Rx onNext");

                                outputWarnings(warnings);
                            }
                        });
                break;
            case R.id.get_min_severity_button:
                apiLayout.setVisibility(View.GONE);
                logLayout.setVisibility(View.VISIBLE);

                int minSeverity = Integer.parseInt(minSeverityEdit.getText().toString());

                FloodMonitoring.getInstance().getWanringsMinSeverity(minSeverity)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Warnings>() {
                            @Override
                            public void onCompleted() {
                                Timber.d("Rx onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.d("Rx onError: " + e.toString());
                            }

                            @Override
                            public void onNext(Warnings warnings) {
                                Timber.d("Rx onNext");

                                outputWarnings(warnings);
                            }
                        });
                break;
            case R.id.get_county_button:
                apiLayout.setVisibility(View.GONE);
                logLayout.setVisibility(View.VISIBLE);

                String county = countySpinner.getSelectedItem().toString();

                log("Selected county: " + county);

                FloodMonitoring.getInstance().getCountyWarnings(county)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Warnings>() {
                            @Override
                            public void onCompleted() {
                                Timber.d("Rx onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.d("Rx onError: " + e.toString());
                            }

                            @Override
                            public void onNext(Warnings warnings) {
                                Timber.d("Rx onNext");

                                outputWarnings(warnings);
                            }
                        });
                break;
            case R.id.get_location_button:
                apiLayout.setVisibility(View.GONE);
                logLayout.setVisibility(View.VISIBLE);

                String lat =  latitudeEdit.getText().toString();
                String lon =  longitudeEdit.getText().toString();
                String distance =  distanceEdit.getText().toString();

                FloodMonitoring.getInstance().getAreaWarnings(Double.parseDouble(lat), Double.parseDouble(lon), Integer.parseInt(distance))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Warnings>() {
                            @Override
                            public void onCompleted() {
                                Timber.d("Rx onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.d("Rx onError: " + e.toString());
                            }

                            @Override
                            public void onNext(Warnings warnings) {
                                Timber.d("Rx onNext");

                                outputWarnings(warnings);
                            }
                        });

                break;
        }
    }

    private void outputWarnings(Warnings warnings){
        log("\n" + DIV);

        if (warnings == null) {
            log("No Warnings object");
            return;
        }

        Meta meta = warnings.getMeta();
        if (meta != null) {
            log("Publisher: " + meta.getPublisher());
            log("Licence: " + meta.getLicence());
            log("Documentation: " + meta.getDocumentation());
            log("Version: " + meta.getVersion());
            log("Comment: " + meta.getComment());
            log(DIV);
        }

        List<WarningItem> items = warnings.getItems();

        int oneTally = 0;
        int twoTally = 0;
        int threeTally = 0;
        int fourTally = 0;
        int fiveTally = 0;

        for (WarningItem item : items) {
            log(DIV);
            log("Warning:\n" + item.toString());

            switch(item.getSeverityLevel()){
                case 1:
                    oneTally++;
                    break;
                case 2:
                    twoTally++;
                    break;
                case 3:
                    threeTally++;
                    break;
                case 4:
                    fourTally++;
                    break;
                case 5:
                    fiveTally++;
                    break;
            }
        }

        log("Current warnings total: " + items.size());
        log("Severity level 1: " + oneTally);
        log("Severity level 2: " + twoTally);
        log("Severity level 3: " + threeTally);
        log("Severity level 4: " + fourTally);
        log("Severity level 5: " + fiveTally);

    }

    private void log(String log){
        Timber.d(log);
        logView.append(log + "\n");

        int excessLineNumber = logView.getLineCount() - 350;
        if (excessLineNumber > 0) {
            int eolIndex = -1;
            CharSequence charSequence = logView.getText();
            for(int i=0; i<excessLineNumber; i++) {
                do {
                    eolIndex++;
                } while(eolIndex < charSequence.length() && charSequence.charAt(eolIndex) != '\n');
            }
            if (eolIndex < charSequence.length()) {
                logView.getEditableText().delete(0, eolIndex+1);
            }
            else {
                logView.setText("");
            }
        }
    }
}
