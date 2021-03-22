package com.cedric.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ForecastAdapter extends ArrayAdapter<Forecast> {

    Context FContext;
    ArrayList<Forecast> forecasts;

    public ForecastAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Forecast> list) {
        super(context, 0, list);
        FContext = context;
        forecasts = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null)
        {
            listItem = LayoutInflater.from(FContext).inflate(R.layout.list_item,parent,false);
        }

        Forecast currentForeCast = forecasts.get(position);

        ImageView icon = listItem.findViewById(R.id.imageView_Icon);
        String iconWithLeading0 = String.format("%02d",currentForeCast.getIcon());
        String url = "https://developer.accuweather.com/sites/default/files/"+iconWithLeading0+"-s.png";
        Picasso
                .get()
                .load(url)
                .into(icon);

        TextView date = listItem.findViewById(R.id.txtView_Date);
        date.setText(currentForeCast.getDate());

        TextView condition = listItem.findViewById(R.id.txtView_IconPhrase);
        condition.setText(currentForeCast.getIconPhrase());

        TextView min = listItem.findViewById(R.id.txtView_Min);
        min.setText(String.valueOf(currentForeCast.getMinTemp()));

        TextView max = listItem.findViewById(R.id.txtView_Max);
        max.setText(String.valueOf(currentForeCast.getMaxTemp()));

        return listItem;
    }
}
