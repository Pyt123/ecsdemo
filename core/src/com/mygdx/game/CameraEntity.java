package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.Viewport;

import ecs.CameraComponent;
import ecs.Entity;

public abstract class CameraEntity extends Entity
{
    protected final int MAX_CAMERA_VIEW_HEIGHT = 100;
    protected float ASPECT_RATIO_X_Y;
    protected int MAX_CAMERA_VIEW_WIDTH;

    protected Camera camera;
    protected Viewport viewport;

    public CameraEntity(Camera camera, Viewport viewport)
    {
        super(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        this.camera = camera;
        this.viewport = viewport;
        ASPECT_RATIO_X_Y = (((float)Gdx.graphics.getWidth())) / Gdx.graphics.getHeight();
        MAX_CAMERA_VIEW_WIDTH = MAX_CAMERA_VIEW_HEIGHT * MAX_CAMERA_VIEW_WIDTH;
        transform.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        Gdx.gl.glClearColor(1, 1, 0, 1);

        attachComponent(new CameraComponent(camera));
    }

    public Camera getCamera()
    {
        return camera;
    }
}
