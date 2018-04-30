package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import ecs.EcsManager;
import ecs.Render;
import ecs.Scene;
import ecs.Time;

public class GameScreen extends ScreenAdapter
{
    // ECS Manager
    private EcsManager ecsManager;

    public GameScreen()
    {
        super();
        ecsManager = new GameEcsManager(Scene.getCamera(), Scene.getViewport());
        ecsManager.start();
    }

	@Override
    public void resize(int width, int height)
    {
        Scene.getViewport().update(width, height);
    }

    @Override
	public void render (float dt)
    {
        super.render(dt);
        Time.setDeltaTimes(dt);
        Time.increaseFrameCounter();
        Scene.getCamera().update();
        Render.getBatch().setProjectionMatrix(Scene.getCamera().combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Render.getBatch().begin();
		ecsManager.nextFrame();
        Render.getBatch().end();
    }
	
	@Override
	public void dispose ()
    {
		Render.getBatch().dispose();
		//ecsManager.dispose();
	}
}
