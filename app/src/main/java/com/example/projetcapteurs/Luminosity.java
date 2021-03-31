package com.example.projetcapteurs;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.SensorEvent;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Luminosity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private View root;
    private float maxValue;

    private TextView lightValue;
    private TextView resultText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luminosity);

//        root = findViewById(R.id.root);
        lightValue = findViewById(R.id.lightValue);
        resultText = findViewById(R.id.resultText);
        imageView = findViewById(R.id.imageView);

        // Instancier le SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Instancier le capteur de lumière
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (lightSensor == null) {
            Toast.makeText(this, "The device has not light sensor :(", Toast.LENGTH_SHORT).show();
            finish();
        }

        maxValue = lightSensor.getMaximumRange();


        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float value = event.values[0];
//                getSupportActionBar().setTitle("Luminosity : " + value + " lx");


                NumberFormat formatter = new DecimalFormat("#0");
                lightValue.setText( formatter.format(value) );


                int newValue = (int) (255f * value / maxValue);
//                root.setBackgroundColor(Color.rgb(newValue, newValue, newValue));

                resultText.setText( getResultText(value) );
            }

            private String getResultText(double luxValue){
                String result = "Minimum pour ";

                if (luxValue < 50){
                    result = "conditions d'éclairage très inférieures au minimum.";
                } else if(luxValue >= 50 && luxValue < 100){
                    result += "garage et éclairage général de la chambre ou du salon.";
                } else if(luxValue >= 100 && luxValue < 150){
                    result += "la cage d'escalier et l'éclairage général de la salle de bains.";
                } else if(luxValue >= 150 && luxValue < 200){
                    result += "hall et zones de circulation.";
                } else if(luxValue >= 200 && luxValue < 300){
                    result += "miroir de salle de bains lumineux";
                } else if(luxValue >= 300 && luxValue < 400){
                    result += "table de travail, pour lire, étudier ou coudre.";
                } else if(luxValue >= 400 && luxValue < 600){
                    result += "des environnements de travail comme la cuisine.";
                } else {
                    result = "Pièces avec un éclairage excessif.";
                }


                Log.d("LightMeter", "resultText: " + result );

                return result;

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        // enregistrer notre écoute du capteur
        super.onResume();
        sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {

        // désenregistrer notre écoute du capteur
        super.onPause();
        sensorManager.unregisterListener(lightEventListener);
    }
}