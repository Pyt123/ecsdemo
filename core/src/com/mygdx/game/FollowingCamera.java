package com.mygdx.game;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import ecs.Entity;
import ecs.FollowingBehaviourComponent;

public class FollowingCamera extends CameraEntity
{
    public FollowingCamera(Camera camera, Viewport viewport, Entity toFollow, Vector2 offset)
    {
        super(camera, viewport);
        attachComponent(new FollowingBehaviourComponent(toFollow, offset, true, false));
    }
}
