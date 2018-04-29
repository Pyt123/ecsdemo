package com.mygdx.game;

import ecs.Entity;
import ecs.Time;

public class TimeScaleManipulator extends Entity
{
    private final float MAX_SCALE = 1f;
    private final float START_SCALE = 0.01f;
    private final float HOW_LONG_TO_MAX = 5f;
    private final float AMOUNT_PER_SEC = (MAX_SCALE - START_SCALE)/HOW_LONG_TO_MAX;     // unscaled secs, realtime

    public TimeScaleManipulator(float xPos, float yPos)
    {
        super(xPos, yPos);
        Time.setTimeScale(START_SCALE);
    }

    @Override
    public void update()
    {
        super.update();
        if(Time.getTimeScale() < MAX_SCALE)
        {
            float ts = Time.getTimeScale() + AMOUNT_PER_SEC * Time.getDt();
            Time.setTimeScale(ts < MAX_SCALE ? ts : MAX_SCALE);
        }
    }
}
