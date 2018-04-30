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
    private Sensor jumpSensor;
    private SensorManager sensorManager;
    private Game game;

	@Override
	protected void onCreate (Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);

		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        jumpSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, jumpSensor, SensorManager.SENSOR_DELAY_GAME);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		game = new Game();
		initialize(game, config);
	}

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        InputProcessor inputProcessor = (InputProcessor)Gdx.input.getInputProcessor();
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
