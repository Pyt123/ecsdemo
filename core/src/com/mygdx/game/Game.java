package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ecs.EcsManager;
import ecs.Time;

public class Game extends ApplicationAdapter
{
    public static final float VIRTUAL_WIDTH = 1920;
    public static final float VIRTUAL_HEIGHT = 1080;

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

        camera = new OrthographicCamera();
        viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);

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
        Time.increaseFrameCounter();
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
