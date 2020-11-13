package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //add a back button for toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });


        EditText usernameEditText = (EditText) findViewById(R.id.editTextTextPersonName);

        //When we click the "Save" button:
        //the app should check if the field is empty or not. If the field if empty, an error message should be displayed to the user
        //If the field is not empty, the city should be persist into the app and the app goes to the previous screen
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUsername = usernameEditText.getText().toString();
                if (sUsername.matches("")) {
                    toastMsg("You must enter a username.");
                    return;
                }
                else {
                    CityModel city = new CityModel(sUsername, "", 0, 0, 0, 0);
                    MainActivity.cities_list.add(city);
                    startActivity(new Intent(AddActivity.this, MainActivity.class));
                }
            }
        });
    }
}