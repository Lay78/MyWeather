package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherCityActivity extends AppCompatActivity {

    TextView tv_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_city);

        tv_city = findViewById(R.id.tv_city);

        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            CityModel cityModel = (CityModel) intent.getSerializableExtra("data");
            tv_city.setText(cityModel.getCity_name());
        }
    }
}