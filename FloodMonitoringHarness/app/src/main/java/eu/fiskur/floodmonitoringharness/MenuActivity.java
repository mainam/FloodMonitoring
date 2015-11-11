package eu.fiskur.floodmonitoringharness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {

    @Bind(R.id.three_day_forecast_button) Button threeDayForecastButton;
    @Bind(R.id.warnings_and_alerts_button) Button warningsAndAlertsButton;
    @Bind(R.id.warnings_and_alerts_demo_button) Button warningsAndAlertsDemoButton;
    @Bind(R.id.measurment_stations_button) Button measurementsStationsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.three_day_forecast_button,
            R.id.warnings_and_alerts_button,
            R.id.warnings_and_alerts_demo_button,
            R.id.measurment_stations_button })
    public void onClick(View view) {
        Intent intent = null;
        switch(view.getId()){
            case R.id.three_day_forecast_button:
                intent = new Intent(MenuActivity.this, ThreeDayForecastActivity.class);
                break;
            case R.id.warnings_and_alerts_button:
                intent = new Intent(MenuActivity.this, AlertsActivity.class);
                break;
            case R.id.warnings_and_alerts_demo_button:
                intent = new Intent(MenuActivity.this, AlertsDemoActivity.class);
                break;
            case R.id.measurment_stations_button:
                intent = new Intent(MenuActivity.this, StationsActivity.class);
                break;
        }

        startActivity(intent);
    }
}
