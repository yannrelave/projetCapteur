package com.example.projetcapteurs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Niveau extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor rotation;
    TextView resultAxeX;
    TextView resultAxeY;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.niveau);
        resultAxeX = findViewById(R.id.tvResultAxeX);
        resultAxeY = findViewById(R.id.tvResultAxeY);
        progressBar = findViewById(R.id.progressBar);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        rotation = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        if (rotation == null) {
            resultAxeX.setText("Capteur rotation indisponible");
        } else{
            sensorManager.registerListener(this, rotation , SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String axeX = "";
        String axeY = "";

        float x = sensorEvent.values[0];
        x = (float) (Math.round(x * 100.0) / 100.0);
        float y = sensorEvent.values[1];
        y = (float) (Math.round(y * 100.0) / 100.0);
        float z = sensorEvent.values[2];
        z = (float) (Math.round(z * 100.0) / 100.0);

        //Axe X
        if(x > 0){
            axeX = "Trop à gauche";
        }else if (x < 0) {
            axeX = "Trop à roite";
        }else {
            axeX = "Centre";
        }
        //Axe Y
        if(y > 0){
            axeY = "Trop haut";
        }else if (y < 0){
            axeY = "Trop bas";
        }else {
            axeY = "Centre";
        }

        resultAxeX.setText("Axe x : " + axeX);
        resultAxeY.setText("Axe y : " + axeY);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, rotation, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
