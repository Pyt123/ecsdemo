package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ecs.EcsManager;
import ecs.Time;

public class Game extends ApplicationAdapter
{
    // Camera and view
    private final int maxCameraViewHeight = 100;
    private float aspectRatioXY;
    private int maxCameraViewWidth;
    private Camera camera;
    private Viewport viewport;

    private final float targetFrameTime = 30;    // miliseconds
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
        setupCameraStuff();
        setupInputAndManager();

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

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		ecsManager.nextFrame();
        batch.end();
	}
	
	@Override
	public void dispose ()
    {
		batch.dispose();
		ecsManager.dispose();
	}

	private void setupCameraStuff()
    {
        batch = new SpriteBatch();
        aspectRatioXY = (float)(Gdx.graphics.getWidth()) / Gdx.graphics.getHeight();
        maxCameraViewWidth = maxCameraViewHeight * maxCameraViewWidth;
        camera = new OrthographicCamera(maxCameraViewWidth, maxCameraViewHeight);
        camera.translate(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        Gdx.gl.glClearColor(1, 1, 0, 1);
    }

    private void setupInputAndManager()
    {
        InputProcessor inputProcessor = new InputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        ecsManager = new GameEcsManager(this, inputProcessor, batch);
    }
}
