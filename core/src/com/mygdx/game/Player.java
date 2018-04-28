package com.mygdx.game;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;

import ecs.BoxCollider2dComponent;
import ecs.Entity;
import ecs.PhysicsComponent;
import ecs.Velocity2dComponent;
import ecs.SpriteComponent;

public class Player extends Entity
{
    private final float MIN_Y_POS = 100f;
    private final float VELOCITY_X = 200f;
    private Velocity2dComponent velocityC;
    private PhysicsComponent physicsComponent;

    public Player(float posX, float posY)
    {
        super(posX, posY);

        SpriteComponent spriteC = new SpriteComponent(new Texture("running_pose.png"));
        attachComponent(spriteC);

        BoxCollider2dComponent boxColliderC = new BoxCollider2dComponent(spriteC);
        attachComponent(boxColliderC);

        velocityC = new Velocity2dComponent(VELOCITY_X, 0);
        attachComponent(velocityC);

        physicsComponent = new PhysicsComponent(velocityC, MIN_Y_POS);
        attachComponent(physicsComponent);
    }

    public void jump()
    {
        physicsComponent.jump();
    }

    public void die()
    {

    }

    @Override
    public void update()
    {
        super.update();
        if(transform.getPosition().y < MIN_Y_POS)
        {
            transform.getPosition().y = MIN_Y_POS;
        }
    }
}
