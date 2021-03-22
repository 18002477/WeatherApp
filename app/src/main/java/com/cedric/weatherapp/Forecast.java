package com.cedric.weatherapp;

import androidx.annotation.NonNull;

public class Forecast {

    private String Date;
    private double MinTemp;
    private double MaxTemp;
    private int Icon;
    private String IconPhrase;
    private String link;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(double minTemp) {
        MinTemp = minTemp;
    }

    public double getMaxTemp() {
        return MaxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        MaxTemp = maxTemp;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public String getIconPhrase() {
        return IconPhrase;
    }

    public void setIconPhrase(String iconPhrase) {
        IconPhrase = iconPhrase;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @NonNull
    @Override
    public String toString(){
        return this.getDate().substring(0,10)+" - Min: "+this.getMinTemp()+" - Max: "+this.getMaxTemp();
    }

}
