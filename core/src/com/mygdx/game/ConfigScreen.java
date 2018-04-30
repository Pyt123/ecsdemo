package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;


import ecs.Render;
import ecs.Scene;
import ecs.Time;

public class ConfigScreen extends Game implements InputProcessor
{
    private Rectangle buttonRectangle;
    private ConfigEcsManager configEcsManager;

    @Override
    public void create()
    {
        Render.setBatch(new SpriteBatch());
        Scene.setupCameraAndViewport(new OrthographicCamera(),
                new StretchViewport(Config.VIRTUAL_WIDTH, Config.VIRTUAL_HEIGHT));
        Render.getBatch().setProjectionMatrix(Scene.getCamera().combined);

        configEcsManager = new ConfigEcsManager(Scene.getCamera(), Scene.getViewport());
        configEcsManager.start();
        buttonRectangle = configEcsManager.getButton().getBounds();
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Vector3 touchPoint = new Vector3();
        Scene.getCamera().unproject(touchPoint.set(screenX, screenY, 0));
        if(buttonRectangle.contains(touchPoint.x, touchPoint.y))
        {
            setScreen(new GameScreen());
        }
        return true;
    }

    @Override
    public void resize(int width, int height)
    {
        Scene.getViewport().update(width, height);
    }

    @Override
    public void render()
    {
        super.render();
        if(getScreen() == null)
        {
            Time.setDeltaTimes(Gdx.graphics.getDeltaTime());
            Time.increaseFrameCounter();
            Scene.getCamera().update();
            Render.getBatch().setProjectionMatrix(Scene.getCamera().combined);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            Render.getBatch().begin();
            configEcsManager.nextFrame();
            Render.getBatch().end();
        }
    }

    @Override
    public void pause()
    {
    }
    @Override
    public void resume()
    {
    }
    @Override
    public void dispose()
    {
        Render.getBatch().dispose();
    }





    @Override
    public boolean keyDown(int keycode)
    {
        return false;
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
