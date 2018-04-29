package com.mygdx.game;

import ecs.Entity;
import ecs.Time;

public class TimeScaleManipulator extends Entity
{
    private final float START_SCALE = 0.01f;
    private final float REGULAR_SCALE = 1f;
    private final float HOW_LONG_TO_REGULAR = 3f;
    private final float AMOUNT_PER_SEC_TO_REGULAR = (REGULAR_SCALE - START_SCALE)/ HOW_LONG_TO_REGULAR; // unscaled secs, realtime
    private final float AMOUNT_PER_SEC_AFTER_REG = 0.01f;

    public TimeScaleManipulator(float xPos, float yPos)
    {
        super(xPos, yPos);
        Time.setTimeScale(START_SCALE);
    }

    @Override
    public void update()
    {
        super.update();
        if(Time.getTimeScale() >= REGULAR_SCALE)
        {
            Time.setTimeScale(Time.getTimeScale() + AMOUNT_PER_SEC_AFTER_REG * Time.getDt());
        }
        else
        {
            float ts = Time.getTimeScale() + AMOUNT_PER_SEC_TO_REGULAR * Time.getDt();
            Time.setTimeScale(ts < REGULAR_SCALE ? ts : REGULAR_SCALE);
        }
    }
}
