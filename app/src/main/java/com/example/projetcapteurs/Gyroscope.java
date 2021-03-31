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

import static android.util.Half.EPSILON;

public class Gyroscope extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    TextView tv;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    SensorEventListener sensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyroscope);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null) {
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
            SensorEventListener _SensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    if(sensorEvent.values[0] >= 0) {
                        tv.setText("N");
                        tv2.setText("E");
                        tv3.setText("S");
                        tv4.setText("O");
                    }
                    if(sensorEvent.values[0] >= 90) {
                        tv.setText("E");
                        tv2.setText("S");
                        tv3.setText("O");
                        tv4.setText("N");
                    }
                    if(sensorEvent.values[0] >= 180) {
                        tv.setText("S");
                        tv2.setText("O");
                        tv3.setText("N");
                        tv4.setText("E");
                    }
                    if(sensorEvent.values[0] >= 270) {
                        tv.setText("O");
                        tv2.setText("N");
                        tv3.setText("E");
                        tv4.setText("S");
                    }

                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
            mSensorManager.registerListener(_SensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            tv.setText("Le capteur n'est pas disponible");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(sensorListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(sensorListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
