package com.example.projetcapteurs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lightActivity(View view){
        Intent monIntent = new Intent(getApplicationContext(), Luminosity.class);
        startActivity(monIntent);
    }
    public void gyroscopeActivity(View view){
        Intent monIntent = new Intent(getApplicationContext(), Gyroscope.class);
        startActivity(monIntent);
    }
    public void pressureActivity(View view){
        Intent monIntent = new Intent(getApplicationContext(), Pressure.class);
        startActivity(monIntent);
    }
    public void niveauActivity(View view){
        Intent monIntent = new Intent(getApplicationContext(), Niveau.class);
        startActivity(monIntent);
    }
}