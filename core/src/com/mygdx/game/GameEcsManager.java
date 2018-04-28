package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import ecs.EcsManager;

public class GameEcsManager extends EcsManager
{
    private InputProcessor inputProcessor;

    public GameEcsManager(ApplicationAdapter context, Batch batch, Camera camera, Viewport viewport)
    {
        super(context, batch, camera, viewport);
    }

    @Override
    public void start()
    {
        super.start();
        Player player = new Player(0, 200);
        FollowingCamera fCamera =  new FollowingCamera(camera, viewport, player, new Vector2(800, 0));
        Background background = new Background(-140, 0, fCamera);

        inputProcessor = new InputProcessor(player);
        Gdx.input.setInputProcessor(inputProcessor);

        addAndStart(background);
        addAndStart(player);
        addAndStart(fCamera);
    }

    protected void handleInput()
    {
        inputProcessor.handleInput();
    }
}