package com.example.projetcapteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor light;
    private Sensor temp;
    private Sensor press;
    TextView resultLight;
    TextView resultTemp;
    TextView resultPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultLight = (TextView) findViewById(R.id.resultLight);
        resultTemp = (TextView) findViewById(R.id.resultTemp);
        resultPress = (TextView) findViewById(R.id.resultPress);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        temp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        press = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        if (light != null) {
            resultLight.setText("Light disponible");
        }
        if (temp != null) {
            resultTemp.setText("Temp disponible");
        }
        if (press != null) {
            resultPress.setText("Press disponible");
        }
    }
}