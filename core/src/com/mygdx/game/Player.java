package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import ecs.BoxCollider2dComponent;
import ecs.Entity;
import ecs.Velocity2dComponent;
import ecs.SpriteComponent;

public class Player extends Entity
{
    public Player(float posX, float posY)
    {
        super(posX, posY);
        int velX = 100, velY = 0;

        SpriteComponent spriteC = new SpriteComponent(new Texture("running_pose.png"));
        attachComponent(spriteC);

        BoxCollider2dComponent boxColliderC = new BoxCollider2dComponent(spriteC);
        attachComponent(boxColliderC);

        Velocity2dComponent moveC = new Velocity2dComponent(velX, velY);
        attachComponent(moveC);
    }
}
