package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import ecs.Entity;
import ecs.Scene;
import ecs.SpriteComponent;

public class Background extends Entity
{
    public Background(float posX, float posY)
    {
        super(posX, posY);
        Texture texture = new Texture("lava_background.png");
        SpriteComponent spriteC = new SpriteComponent(texture);
        
        Entity bg1 = new Entity(posX, posY);
        bg1.attachComponent(spriteC);
        Entity bg2 = new Entity(posX + texture.getWidth(), posY);
        bg2.attachComponent(spriteC);
        Scene.getEcsManager().addAndStart(bg1);
        Scene.getEcsManager().addAndStart(bg2);
    }
}
