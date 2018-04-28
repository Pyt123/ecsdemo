package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import ecs.Entity;
import ecs.SpriteComponent;

public class Background extends Entity
{
    public Background(float posX, float posY)
    {
        super(posX, posY);
        SpriteComponent spriteC1 = new SpriteComponent(new Texture("lava_background.png"));
        attachComponent(spriteC1);
    }
}
