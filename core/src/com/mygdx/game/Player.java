package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import ecs.BoxCollider2dComponent;
import ecs.Entity;
import ecs.PhysicsComponent;
import ecs.Velocity2dComponent;
import ecs.SpriteComponent;

public class Player extends Entity
{
    private final float VELOCITY_X = 300f;
    private Velocity2dComponent velocityC;
    private PhysicsComponent physicsComponent;
    private BoxCollider2dComponent colliderComponent;

    public Player(float posX, float posY)
    {
        super(posX, posY);

        SpriteComponent spriteC = new SpriteComponent(new Texture("running_pose.png"));
        attachComponent(spriteC);

        colliderComponent = new BoxCollider2dComponent(spriteC);
        attachComponent(colliderComponent);

        velocityC = new Velocity2dComponent(VELOCITY_X, 0);
        attachComponent(velocityC);

        physicsComponent = new PhysicsComponent(velocityC, colliderComponent);
        attachComponent(physicsComponent);
    }

    public void jump()
    {
        physicsComponent.jump();
    }

    public void die()
    {

    }

    public BoxCollider2dComponent getColliderComponent()
    {
        return colliderComponent;
    }
}
