package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import ecs.EcsManager;

public class GameEcsManager extends EcsManager
{
    public GameEcsManager(ApplicationAdapter context, InputProcessor inputProcessor, Batch batch, Camera camera, Viewport viewport)
    {
        super(context, inputProcessor, batch, camera, viewport);
    }

    @Override
    public void start()
    {
        Background background = new Background(0, 0);
        Player player = new Player(0, 0);
        FollowingCamera fCamera =
                new FollowingCamera(this.camera, viewport, player, new Vector2(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/5));

        entities.add(background);
        entities.add(player);
        entities.add(fCamera);

        player.start();
        background.start();
        fCamera.start();
    }

    protected void handleInput()
    {
        InputProcessor inputProcessor = (InputProcessor)this.inputProcessor;
        if(inputProcessor.wasTouched)
        {
            inputProcessor.wasTouched = false;
            entities.get(1).getTransform().setPosition(inputProcessor.xPos, inputProcessor.yPos);
        }
    }
}
