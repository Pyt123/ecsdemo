package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.Viewport;

import ecs.EcsManager;
import ecs.Entity;
import ecs.Scene;
import ecs.SpriteComponent;

public class ConfigEcsManager extends EcsManager
{
    private Button button;

    public ConfigEcsManager(Camera camera, Viewport viewport)
    {
        super(camera, viewport);
    }

    @Override
    public void start()
    {
        super.start();

        Entity background = new Entity(0,0);
        background.attachComponent(new SpriteComponent(new Texture("config_background.jpg")));
        button = new Button(100, 100, new Texture("start_button.png"));
        addAndStart(background);
        addAndStart(button);
        addAndStart(new CameraEntity(Scene.getCamera(), Scene.getViewport()));
    }

    public Button getButton()
    {
        return button;
    }

    @Override
    public void nextFrame()
    {
        super.nextFrame();
    }

    @Override
    public void dispose()
    {
        super.dispose();
    }

    @Override
    protected void handleInput()
    {
    }

    @Override
    public void restart()
    {
    }
}
