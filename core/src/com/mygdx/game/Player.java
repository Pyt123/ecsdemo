package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import ecs.BoxCollider2dComponent;
import ecs.Drawable;
import ecs.Entity;
import ecs.Move2dComponent;
import ecs.NecessaryComponentNotAttachedException;
import ecs.Position2dComponent;
import ecs.Render;
import ecs.SpriteComponent;
import ecs.Updatable;

public class Player extends Entity implements Updatable, Drawable
{
    public Player()
    {
    }

    public void start() throws NecessaryComponentNotAttachedException
    {
        int posX = 0, posY = 0;
        int velX = 5, velY = 3;

        Position2dComponent positionC = new Position2dComponent(posX, posY);
        attachComponent(Position2dComponent.class, new Position2dComponent(posX, posY));

        Texture texture = new Texture("player.png");
        SpriteComponent sc = new SpriteComponent(texture, posX, posY);
        attachComponent(SpriteComponent.class, sc);

        BoxCollider2dComponent boxColliderC = new BoxCollider2dComponent(0, 0,
                sc.getSprite().getRegionX(), sc.getSprite().getRegionY());
        attachComponent(BoxCollider2dComponent.class, boxColliderC);

        Move2dComponent moveC = new Move2dComponent(velX, velY);
        attachComponent(Move2dComponent.class, moveC);
    }
}
