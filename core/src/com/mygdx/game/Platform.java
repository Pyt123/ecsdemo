package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import ecs.BoxCollider2dComponent;
import ecs.Entity;
import ecs.SpriteComponent;

public class Platform extends Entity
{
    private float width;

    public Platform(float xPos, float yPos, Player player)
    {
        super(xPos, yPos);
        SpriteComponent spriteC = new SpriteComponent(new Texture("platform1.png"));
        attachComponent(spriteC);
        BoxCollider2dComponent colliderC = new BoxCollider2dComponent(spriteC);
        attachComponent(colliderC);
        player.getColliderComponent().addColliderWhichCanCollideWith(colliderC);
        width = spriteC.getSprite().getWidth();
    }

    public float getWidth()
    {
        return width;
    }
}
