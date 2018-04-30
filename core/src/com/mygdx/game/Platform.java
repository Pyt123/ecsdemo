package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import ecs.BoxCollider2dComponent;
import ecs.Entity;
import ecs.SpriteComponent;

public class Platform extends Entity
{
    private SpriteComponent spriteC;

    public Platform(float xPos, float yPos, Texture texture, Player player, boolean colliderActive)
    {
        super(xPos, yPos);
        spriteC = new SpriteComponent(texture);
        attachComponent(spriteC);
        BoxCollider2dComponent colliderC = new BoxCollider2dComponent(spriteC, this);
        colliderC.setActive(colliderActive);
        attachComponent(colliderC);
        player.getColliderComponent().addColliderWhichCanCollideWith(colliderC);
    }

    public float getWidth()
    {
        return spriteC.getSprite().getWidth();
    }

    public void changeTexture(Texture texture, boolean leaveSize)
    {
        spriteC.changeTexture(texture, leaveSize);
    }
}
