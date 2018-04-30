package com.mygdx.game;

import com.badlogic.gdx.Input;

import ecs.Scene;

class InputProcessor implements com.badlogic.gdx.InputProcessor
{
    public enum LightState { DARK, BRIGHT };

    private Player player;
    private PlatformGenerator platformGenerator;
    private Background background;

    private boolean wasTouched = false;

    private final int MAX_LUX = 4000;
    private int lux = 1000;

    private boolean lightChanged = false;
    private final float CHANGE_VALUE = 2000f;
    private LightState lightState = LightState.DARK;

    public InputProcessor(Player player, Background background)
    {
        this.player = player;
        this.background = background;
    }

    public void setPlatformGenerator(PlatformGenerator platformGenerator)
    {
        this.platformGenerator = platformGenerator;
    }

    public void handleInput()
    {
        handleTouch();
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

    private void handleTouch()
    {
        if(wasTouched)
        {
            player.jump();
            wasTouched = false;
            //lux += MAX_LUX/2;
            //setLightState((lux)%MAX_LUX);
        }
    }

    public void setLightState(float lux)
    {
        if(lux >= CHANGE_VALUE)
        {
            if(this.lightState == LightState.DARK)
            {
                this.lightState = LightState.BRIGHT;
                lightChanged = true;
                if(platformGenerator != null) { platformGenerator.changePlatformsState(); }
                background.changeBrightness();
            }
        }
        else
        {
            if (this.lightState == LightState.BRIGHT)
            {
                this.lightState = LightState.DARK;
                lightChanged = true;
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
        wasTouched = true;
        return true;
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
