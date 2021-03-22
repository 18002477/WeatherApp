package com.cedric.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ForecastUtility fc = new ForecastUtility();
        fc.execute();


        ArrayList<Forecast> forecasts = fc.ParseJSON();

        ListView forecastListView = findViewById(R.id.lvForecast);

        /*String[] forecastStringArray = new String[5];

        for (int i=0; i<forecasts.size();i++){
            forecastStringArray[i] = forecasts.get(i).toString();

        }

        ArrayAdapter<String> forecastArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,forecastStringArray);
        forecastListView.setAdapter(forecastArrayAdapter);*/

        ForecastAdapter forecastAdapter = new ForecastAdapter(this,0,forecasts);
        forecastListView.setAdapter(forecastAdapter);
    }
}