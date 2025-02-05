package com.example.tp4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherCityActivity extends AppCompatActivity {

    TextView tv_city, tv_temperature, tv_feels_like, tv_temp_min, tv_temp_max;
    private String city_name;
    private ImageView imageView;

    //with Volley
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_city);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //back button for toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mQueue = Volley.newRequestQueue(this);

        //add a back button for toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeatherCityActivity.this, MainActivity.class));
            }
        });

        tv_city = findViewById(R.id.tv_city);
        tv_temperature = findViewById(R.id.tv_temperature);
        tv_feels_like = findViewById(R.id.tv_feels_like);
        tv_temp_min = findViewById(R.id.tv_temp_min);
        tv_temp_max = findViewById(R.id.tv_temp_max);

        imageView = (ImageView)findViewById(R.id.imageView);

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
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray jsonArray = response.getJSONArray("weather");
                    JSONObject icon_object = jsonArray.getJSONObject(0);

                    //temperature
                    double temp_double = main_object.getDouble("temp");
                    int temp_int = (int)Math.round(temp_double);
                    tv_temperature.setText(String.valueOf(temp_int) + "°C");

                    //felt temperature
                    double felt_double = main_object.getDouble("feels_like");
                    int felt_int = (int)Math.round(felt_double);
                    tv_feels_like.setText("feels like " + String.valueOf(felt_int) + "°C");

                    //min temperature
                    double min_double = main_object.getDouble("temp_min");
                    int min_int = (int)Math.round(min_double);
                    tv_temp_min.setText("↓ Min. " + String.valueOf(min_int) + "°C");

                    //max temperature
                    double max_double = main_object.getDouble("temp_max");
                    int max_int = (int)Math.round(max_double);
                    tv_temp_max.setText("↑ Max. " + String.valueOf(max_int) + "°C");


                    //weather image
                    String icon = icon_object.getString("icon");
                    String namefile = "img_" + icon;
                    String uri = "@drawable/" + namefile; //warning: remove extension of the file
                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    imageView.setImageDrawable(res);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
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
            showAlertDialog();
            return true;
        }

        if (id == R.id.home){
            Intent intent = new Intent(WeatherCityActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //configure the AlertDialog when we click on "Delete" button
    public void showAlertDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Warning!");
        alert.setMessage("Are you sure you want to delete this city from your favorites?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.cities_list.removeIf(c -> c.getCity_name().equals(city_name));
                startActivity(new Intent(WeatherCityActivity.this, MainActivity.class));
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        alert.create().show();
    }
}