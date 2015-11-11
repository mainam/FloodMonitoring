package eu.fiskur.floodmonitoringharness;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.filosganga.geogson.model.Geometry;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.fiskur.floodmonitoringapi.FloodMonitoring;
import eu.fiskur.floodmonitoringapi.model.Polygon;
import eu.fiskur.floodmonitoringapi.model.WarningItem;
import eu.fiskur.floodmonitoringapi.model.Warnings;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class AlertsDemoActivity extends AppCompatActivity {

    @Bind(R.id.demo_alerts_list) ListView demoListView;

    List<WarningItem> warningItems;
    WarningsListAdapter warningsAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alerts_demo);

        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);

        Timber.plant(new Timber.DebugTree());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        FloodMonitoring.getInstance().getAllWarnings()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(new Observer<Warnings>() {
                @Override
                public void onCompleted() {
                    Timber.d("Rx onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    log("Rx onError: " + e.toString());
                }

                @Override
                public void onNext(Warnings warnings) {
                    log("Rx onNext");
                    handleWarnings(warnings);
                }
            });
    }

    private void handleWarnings(Warnings warnings){
        warningItems = warnings.getItems();
        warningsAdapter = new WarningsListAdapter(this, warningItems);
        demoListView.setAdapter(warningsAdapter);

        demoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WarningItem warning = warningsAdapter.getItem(position);
                log("Clicked: " + warning.getDescription());

                //Just checking GeoJSON works:
                FloodMonitoring.getInstance().getGeoJSON(warning.getFloodArea().getPolygon())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(new Observer<Polygon>() {
                            @Override
                            public void onCompleted() {
                                Timber.d("Rx onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                log("Rx onError: " + e.toString());
                            }

                            @Override
                            public void onNext(Polygon polygon) {

                                log("Rx onNext: " + polygon.getType());
                            }
                        });
            }
        });
    }

    private void log(String message){
        Timber.d(message);
    }
}
