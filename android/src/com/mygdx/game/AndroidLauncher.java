package com.mygdx.game;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication implements SensorEventListener
{
    private Sensor lightSensor;
    private Sensor accelerometerSensor;
    private SensorManager sensorManager;
    private GameScreen gameScreen;

	@Override
	protected void onCreate (Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);

		setupSensors();
        startGame();
	}


	private void setupSensors()
    {
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    private void startGame()
    {
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new ConfigScreen(), config);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        GameplayInputProcessor inputProcessor;
        try { inputProcessor = (GameplayInputProcessor)Gdx.input.getInputProcessor(); }
        catch (Exception e) { return; }

        if(inputProcessor != null)
        {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT)
            {
                inputProcessor.setLightState(event.values[0]);
            }
            else if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            {
                inputProcessor.handleAccelometer(event.values[0], event.values[1], event.values[2]);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    }
}
