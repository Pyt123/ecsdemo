package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.Viewport;

import ecs.CameraComponent;
import ecs.Entity;

public abstract class CameraEntity extends Entity
{
    protected Camera camera;
    protected Viewport viewport;

    public CameraEntity(Camera camera, Viewport viewport)
    {
        super(Game.VIRTUAL_WIDTH/2, Game.VIRTUAL_HEIGHT/2);
        this.camera = camera;
        this.viewport = viewport;
        Gdx.gl.glClearColor(0, 0, 0, 1);

        attachComponent(new CameraComponent(camera));
    }

    public Camera getCamera()
    {
        return camera;
    }
}
