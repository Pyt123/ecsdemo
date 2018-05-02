package com.mygdx.game;

import com.badlogic.gdx.Input;

import ecs.Scene;

class GameplayInputProcessor implements com.badlogic.gdx.InputProcessor
{
    public enum LightState { DARK, BRIGHT };

    private Player player;
    private PlatformGenerator platformGenerator;
    private Background background;

    private final float LUX_CHANGE_VALUE = 100f;
    private final float ACC_TO_JUMP = 1.5f;
    private final float ACC_TO_SHOT = -1.5f;
    private float lastValue = 0;

    private LightState lightState = LightState.DARK;

    public GameplayInputProcessor(Player player, Background background)
    {
        this.player = player;
        this.background = background;
    }

    public void setPlatformGenerator(PlatformGenerator platformGenerator)
    {
        this.platformGenerator = platformGenerator;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        if(Input.Keys.R == keycode)
        {
            Scene.getEcsManager().restart();
            return true;
        }
        return false;
    }

    public void handleAccelometer(float x, float y, float z)
    {
        if(y > ACC_TO_JUMP)
        {
            player.jump();
        }
        else if(y < ACC_TO_SHOT && lastValue >= ACC_TO_SHOT)
        {
            player.shot();
        }

        lastValue = y;
    }

    public void setLightState(float lux)
    {
        if(lux >= LUX_CHANGE_VALUE)
        {
            if(this.lightState == LightState.DARK)
            {
                this.lightState = LightState.BRIGHT;
                if(platformGenerator != null) { platformGenerator.changePlatformsState(); }
                background.changeBrightness();
            }
        }
        else
        {
            if (this.lightState == LightState.BRIGHT)
            {
                this.lightState = LightState.DARK;
                if(platformGenerator != null) { platformGenerator.changePlatformsState(); }
                background.changeBrightness();
            }
        }
    }

    public LightState getLightState()
    {
        return lightState;
    }







    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }
    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }
    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
