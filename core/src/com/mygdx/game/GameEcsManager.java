package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.Batch;

import ecs.EcsManager;
import ecs.NecessaryComponentNotAttachedException;

public class GameEcsManager extends EcsManager
{
    public GameEcsManager(ApplicationAdapter context, com.badlogic.gdx.InputProcessor inputProcessor, Batch batch)
    {
        super(context, inputProcessor, batch);
    }

    @Override
    public void start()
    {
        try
        {
            Player player = new Player();
            Background background = new Background();
            entities.add(background);
            entities.add(player);

            player.start();
            background.start();
        }
        catch (NecessaryComponentNotAttachedException e)
        {
            e.printStackTrace();
            System.exit(666);
        }

    }
}
