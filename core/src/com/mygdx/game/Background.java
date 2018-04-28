package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import ecs.Entity;
import ecs.Scene;
import ecs.SpriteComponent;

public class Background extends Entity
{
    private SpriteComponent spriteC;
    private Entity [] backgrounds = new Entity[2];
    private int nextToChange = 0;
    private CameraEntity cameraEntity;

    public Background(float posX, float posY, CameraEntity cameraEntity)
    {
        super(posX, posY);
        this.cameraEntity = cameraEntity;
        Texture texture = new Texture("lava_background.png");
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        spriteC = new SpriteComponent(texture);
        SpriteComponent spriteC2 = new SpriteComponent(texture);

        backgrounds[0] = new Entity(posX, posY);
        backgrounds[0].attachComponent(spriteC);
        backgrounds[1] = new Entity(posX + texture.getWidth(), posY);
        backgrounds[1].attachComponent(spriteC2);

        Scene.getEcsManager().addAndStart(backgrounds[0]);
        Scene.getEcsManager().addAndStart(backgrounds[1]);
    }

    @Override
    public void update()
    {
        Entity bg = backgrounds[nextToChange];
        float edgeCameraX =
                cameraEntity.getTransform().getPosition().x - (cameraEntity.getCamera().viewportWidth/2);
        float edgeBgX = bg.getTransform().getPosition().x + spriteC.getSprite().getWidth();
        if(edgeBgX < edgeCameraX)
        {
            nextToChange = (nextToChange + 1) % backgrounds.length;
            bg.getTransform().setPosition(bg.getTransform().getPosition().x + backgrounds.length * spriteC.getSprite().getWidth(),
                    bg.getTransform().getPosition().y);
        }

        super.update();
    }
}
