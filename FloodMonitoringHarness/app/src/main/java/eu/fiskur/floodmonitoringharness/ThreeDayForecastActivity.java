package eu.fiskur.floodmonitoringharness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.fiskur.floodmonitoringapi.FloodApiLogger;
import eu.fiskur.floodmonitoringapi.FloodMonitoring;
import eu.fiskur.floodmonitoringapi.model.ThreeDayForecast;
import eu.fiskur.floodmonitoringapi.model.ThreeDayForecastObj;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class ThreeDayForecastActivity extends AppCompatActivity {

    @Bind(R.id.scroll_layout) ScrollView scrollLayout;
    @Bind(R.id.api_layout) LinearLayout apiLayout;

    @Bind(R.id.get_3_day_forecast_button) Button threeDayForecastButton;


    @Bind(R.id.log_layout) LinearLayout logLayout;
    @Bind(R.id.log_view) EditText logView;
    @Bind(R.id.log_clear_button) Button clearLogButton;
    @Bind(R.id.log_close_button) Button closeLogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_three_day_forecast);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.log_clear_button,
            R.id.log_close_button,
            R.id.get_3_day_forecast_button})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.log_clear_button:
                logView.setText("");
                apiLayout.setVisibility(View.VISIBLE);
                logLayout.setVisibility(View.GONE);
                break;
            case R.id.log_close_button:
                apiLayout.setVisibility(View.VISIBLE);
                logLayout.setVisibility(View.GONE);
                break;
            case R.id.get_3_day_forecast_button:
                apiLayout.setVisibility(View.GONE);
                logLayout.setVisibility(View.VISIBLE);
                FloodMonitoring.getInstance().getThreeDayForecast()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(new Observer<ThreeDayForecast>() {
                            @Override
                            public void onCompleted() {
                                Timber.d("Rx onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.d("Rx onError: " + e.toString());
                            }

                            @Override
                            public void onNext(ThreeDayForecast threeDayForecast) {
                                Timber.d("Rx onNext");

                                outputForecast(threeDayForecast);
                            }
                        });
                break;
        }
    }

    private void outputForecast(ThreeDayForecast threeDayForecast){
        log("\n");

        if (threeDayForecast == null) {
            log("No Warnings object");
            return;
        }

        log("@context: " + threeDayForecast.getContext());
        log("meta: " + threeDayForecast.getMeta().toString());

        ThreeDayForecastObj forecast = threeDayForecast.get3DayForecast();
        if(forecast != null) {
            log("ThreeDayForecast: " + forecast.toString());
        }

    }

    private void log(String log) {
        Timber.d(log);
        logView.append(log + "\n");
    }
}
