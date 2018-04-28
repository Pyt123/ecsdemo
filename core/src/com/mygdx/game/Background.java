package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import ecs.Drawable;
import ecs.Entity;
import ecs.NecessaryComponentNotAttachedException;
import ecs.Render;
import ecs.SpriteComponent;

public class Background extends Entity implements Drawable
{
    private SpriteComponent spriteComponent;

    public Background() throws NecessaryComponentNotAttachedException
    {
        int posX = -100, posY = -100;
        Texture texture = new Texture("lava_background.png");
        spriteComponent = new SpriteComponent(texture, posX, posY, texture.getWidth(), texture.getHeight());
        attachComponent(SpriteComponent.class, spriteComponent);
    }

    /*@Override
    public void draw()
    {
        spriteComponent.draw(Render.getBatch());
    }*/
}
