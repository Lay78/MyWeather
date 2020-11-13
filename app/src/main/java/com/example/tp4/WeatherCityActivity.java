package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class WeatherCityActivity extends AppCompatActivity {

    TextView tv_city;
    private String city_name;

    //with Volley
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_city);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mQueue = Volley.newRequestQueue(this);

        //add a back button for toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeatherCityActivity.this, MainActivity.class));
            }
        });

        tv_city = findViewById(R.id.tv_city);

        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            CityModel cityModel = (CityModel) intent.getSerializableExtra("data");
            city_name = cityModel.getCity_name();
            getSupportActionBar().setTitle(city_name);
            tv_city.setText(city_name);

            //call JSON
            jsonParse(city_name);
        }
    }

    private void jsonParse(String city_name) {
        String API_key = "bbbec360655b623637adfdfadab2ba7d";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city_name + "&units=metric&appid=" + API_key;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}