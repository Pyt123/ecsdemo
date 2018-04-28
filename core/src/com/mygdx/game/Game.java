package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ecs.EcsManager;
import ecs.Time;

public class Game extends ApplicationAdapter
{
    private Camera camera;
    private Viewport viewport;
    //////////////////////////////////////////////////
    // Batch
    private SpriteBatch batch;
    //////////////////////////////////////////////////
    // ECS Manager
    private EcsManager ecsManager;
    //////////////////////////////////////////////////

	@Override
	public void create ()
    {
        batch = new SpriteBatch();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        ecsManager = new GameEcsManager(this, batch, camera, viewport);
        ecsManager.start();
    }

	@Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

	@Override
	public void render ()
    {
        Time.setDeltaTimes(Gdx.graphics.getDeltaTime());

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		ecsManager.nextFrame();
        batch.end();
	}
	
	@Override
	public void dispose ()
    {
		//batch.dispose();
		//ecsManager.dispose();
	}
}
