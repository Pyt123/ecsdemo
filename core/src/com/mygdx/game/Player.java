package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ecs.Animation2dComponent;
import ecs.BoxCollider2dComponent;
import ecs.Entity;
import ecs.PhysicsComponent;
import ecs.Scene;
import ecs.Velocity2dComponent;

public class Player extends Entity
{
    private final float VELOCITY_X = 400f;
    private final Vector2 JUMP_VECTOR = new Vector2(0f, 1500f);
    private Velocity2dComponent velocityC;
    private PhysicsComponent physicsComponent;
    private BoxCollider2dComponent colliderComponent;

    public Player(float posX, float posY)
    {
        super(posX, posY);

        Animation2dComponent animationC =
                new Animation2dComponent(new Texture("animation_player.png"), 8, 0.1f);
        attachComponent(animationC);

        velocityC = new Velocity2dComponent(VELOCITY_X, 0);
        attachComponent(velocityC);

        colliderComponent = new BoxCollider2dComponent(animationC.getFrameWidth(), animationC.getFrameHeight(), this);
        attachComponent(colliderComponent);

        physicsComponent = new PhysicsComponent(velocityC, colliderComponent);
        attachComponent(physicsComponent);
    }

    public void jump()
    {
        if(physicsComponent.getIsGrounded())
        {
            physicsComponent.addForce(JUMP_VECTOR);
        }
    }

    @Override
    public void update()
    {
        super.update();
        if(transform.getPosition().y + colliderComponent.getBounds().getHeight() < 0)
        {
            Scene.getEcsManager().restart();
        }
    }

    public void die()
    {

    }

    public BoxCollider2dComponent getColliderComponent()
    {
        return colliderComponent;
    }
}
