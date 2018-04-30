package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import ecs.SpriteComponent;

public class Button extends ecs.Entity
{
    private SpriteComponent spriteComponent;

    public Button(float xPos, float yPos, Texture texture)
    {
        super(Config.VIRTUAL_WIDTH/2 - texture.getWidth()/2, Config.VIRTUAL_HEIGHT/2 - texture.getHeight()/2);
        spriteComponent = new SpriteComponent(texture);
        attachComponent(spriteComponent);
    }

    public Rectangle getBounds()
    {
        Sprite sprite = spriteComponent.getSprite();
        return new Rectangle(sprite.getX() + getTransform().getPosition().x, sprite.getY() + getTransform().getPosition().y,
                sprite.getWidth(), sprite.getHeight());
    }
}
