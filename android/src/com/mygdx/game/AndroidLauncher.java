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
    private Sensor sensor;
    private SensorManager sensorManager;
    private Game game;

	@Override
	protected void onCreate (Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);

		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		game = new Game();
		initialize(game, config);
	}

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if(sensor.getType() == Sensor.TYPE_LIGHT)
        {
            InputProcessor inputProcessor = (InputProcessor)Gdx.input.getInputProcessor();
            if(inputProcessor != null)
            {
                inputProcessor.setLightState(event.values[0]);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
}
