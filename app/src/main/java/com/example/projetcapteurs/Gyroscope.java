package com.example.projetcapteurs;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Gyroscope extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    TextView tv;
    View view;
    boolean accelerometreExiste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyroscope);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            accelerometreExiste = true;
            SensorEventListener _SensorEventListener=   new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    if(sensorEvent.values[0] > 2) {
                        view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    }
                    else if(sensorEvent.values[0] < - 2){
                        view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    }
                    else {
                        view.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    }
                    tv.setText(sensorEvent.values[0] + "");
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
            mSensorManager.registerListener(_SensorEventListener , mSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            tv.setText("Le capteur n'existe pas");
            view.setBackgroundColor(11);
            accelerometreExiste = false;
        }
    }

    /*@Override
    public void onPause(){
        super.onPause();
        if(accelerometreExiste){
            mSensorManager.unregisterListener(this);
        }
    }*/
}
