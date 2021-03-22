package com.cedric.weatherapp;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ForecastUtility extends AsyncTask<Void, Void, Void> {

    private static final String BASE_URL = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/";
    private static final String LOCATION_KEY = "305605";
    private static final String API_KEY = "188ynxhcnwqGQg8YFNKjKAeLrDQ22FwM";
    private static final String METRIC_VALUE = "true";

    private String response;

    public URL buildURL() {
        URL url = null;
        Uri uriBuild = Uri.parse(BASE_URL + LOCATION_KEY).buildUpon()
                .appendQueryParameter("apikey", API_KEY)
                .appendQueryParameter("metric", METRIC_VALUE)
                .build();

        try {
            url = new URL(uriBuild.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i("ForcastUtil - buildURL", "" + url);
        return url;
    }

    @SuppressLint("LongLogTag")
    public String processHttpResponse(URL u) {
        String response = "";
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection();
            InputStream in = urlConnection.getInputStream();

            Scanner scan = new Scanner(in);
            scan.useDelimiter("//A");

            if (scan.hasNext()) {
                response = scan.next();
            }
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("ForcastUtil - ProcessHttp", "" + response);
        return response;

    }

    @SuppressLint("LongLogTag")
    @Override
    protected Void doInBackground(Void... voids) {
        URL url = buildURL();
        response = processHttpResponse(url);
        Log.i("ForcastUtil - doBackground", "" + response);
        return null;
    }

    @SuppressLint("LongLogTag")
    public ArrayList<Forecast> ParseJSON() {
        ArrayList<Forecast> forecasts = new ArrayList<>();
        if (response == null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            JSONObject apiResult = new JSONObject(response);

            JSONArray apiResultArray = apiResult.getJSONArray("DailyForecasts");

            for (int i = 0; i < apiResultArray.length(); i++) {
                // Single Forecast
                JSONObject apiResultObject = apiResultArray.getJSONObject(i);

                // temp object
                Forecast tempF = new Forecast();

                // date
                String date = apiResultObject.getString("Date");
                tempF.setDate(date);


                // min and max temp from Temperature JSON Object
                JSONObject tempForecast = apiResultObject.getJSONObject("Temperature");
                String minTemp = tempForecast.getJSONObject("Minimum").getString("Value");
                tempF.setMinTemp(Double.parseDouble(minTemp));

                String maxTemp = tempForecast.getJSONObject("Maximum").getString("Value");
                tempF.setMaxTemp(Double.parseDouble(maxTemp));

                // Link
                String link = apiResultObject.getString("Link");

                // icon and icon phrase from the Day JSON Object
                JSONObject iconForecast = apiResultObject.getJSONObject("Day");
                String icon = iconForecast.getString("Icon");
                tempF.setIcon(Integer.parseInt(icon));

                String iconPhrase = iconForecast.getString("IconPhrase");
                tempF.setIconPhrase(iconPhrase);

                forecasts.add(tempF);
                Log.i("ForecastUtil - ParseJSON:",""+tempF.toString());

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forecasts;
    }
}
