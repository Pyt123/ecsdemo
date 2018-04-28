package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import ecs.Entity;
import ecs.Render;
import ecs.Scene;
import ecs.SpriteComponent;

public class Background extends Entity
{
    private SpriteComponent spriteC1;
    private float posX;

    public Background(float posX, float posY)
    {
        super(posX, posY);
        Texture texture = new Texture("lava_background.png");
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        spriteC1 = new SpriteComponent(texture);
        //SpriteComponent spriteC2 = new SpriteComponent(texture);

        //Entity bg1 = new Entity(posX, posY);
        attachComponent(spriteC1);
        //Entity bg2 = new Entity(posX + texture.getWidth(), posY);
        //bg2.attachComponent(spriteC2);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        //Scene.getEcsManager().addAndStart(bg1);
        //Scene.getEcsManager().addAndStart(bg2);
    }

    public void draw()
    {
        Render.getBatch().draw(spriteC1.getSprite(), posX, 0);
    }
}
