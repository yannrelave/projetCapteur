package com.example.projetcapteurs;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Pressure extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    TextView result;
    Sensor pressure;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pressure);
        result = findViewById(R.id.tvResult);
        image = findViewById(R.id.imageView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        if (pressure == null) {
            result.setText("Capteur pression indisponible");
        } else{
            sensorManager.registerListener(this, pressure , SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float pressureVal = (float) (Math.round((sensorEvent.values[0]) * 10.0) / 10.0);
        // float pressureVal = 980;
        if(pressureVal < 985){
            // Tempete
            image.setImageResource(R.mipmap.tempete);
        } else if (pressureVal < 1005) {
            // Pluie ou vent 985 1005
            image.setImageResource(R.mipmap.nuage);
        }
        else if (pressureVal < 1025) {
            // Variable 1005 1025
            image.setImageResource(R.mipmap.variable);
        }
        else if (pressureVal < 1040) {
            // Beau temps 1025 1040
            image.setImageResource(R.mipmap.soleil);
        }
        else {
            // Tres sec
            image.setImageResource(R.mipmap.sec);
        }
        result.setText(pressureVal + " hPa (mbar)");

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
        sensorManager.registerListener(this, pressure, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
