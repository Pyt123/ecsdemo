package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

import ecs.Entity;
import ecs.Scene;

public class PlatformGenerator extends Entity
{
    private final int MIN_SPACE = 400;
    private final int MAX_SPACE = 700;
    private final int PLATFORMS_COUNT = 5;
    private final Random random = new Random();
    private final Platform [] platforms = new Platform[PLATFORMS_COUNT];
    private final Texture DARK_TEXTURE = new Texture("platform_dark.png");
    private final Texture BRIGHT_TEXTURE = new Texture("platform_bright.png");

    private int toCheckIndex = 0;
    private CameraEntity camera;

    public PlatformGenerator(float xPos, float yPos, Player player, CameraEntity camera)
    {
        super(xPos, yPos);

        this.camera = camera;
        platforms[0] = new Platform(xPos, yPos, DARK_TEXTURE, player);
        Scene.getEcsManager().addAndStart(platforms[0]);
        for(int i = 1; i < platforms.length; i++)
        {
            Scene.getEcsManager().addAndStart(instantiatePlatform(i, player));
        }
    }

    private Platform instantiatePlatform(int index, Player player)
    {
        int prevIndex = (platforms.length + index -1)%platforms.length;
        float prevPosX = platforms[prevIndex].getTransform().getPosition().x;


        float posX = prevPosX + MIN_SPACE + random.nextInt(MAX_SPACE - MIN_SPACE);
        platforms[index] = new Platform(posX, getTransform().getPosition().y, chooseTexture(), player);
        return platforms[index];
    }

    private void repositionPlatform(int index)
    {
        int prevIndex = (platforms.length + index -1)%platforms.length;
        float prevPosX = platforms[prevIndex].getTransform().getPosition().x;

        float posX = prevPosX + MIN_SPACE + random.nextInt(MAX_SPACE - MIN_SPACE);
        platforms[index].getTransform().setPosition(posX, getTransform().getPosition().y);
        platforms[index].changeTexture(chooseTexture(), true);
    }

    private void checkIfOutOfScreen(int index)
    {
        if(platforms[index].getTransform().getPosition().x + platforms[index].getWidth()
                < camera.getTransform().getPosition().x - camera.getCamera().viewportWidth/2)
        {
            repositionPlatform(toCheckIndex);
            toCheckIndex = (toCheckIndex + 1) % platforms.length;
        }
    }

    private Texture chooseTexture()
    {
        if(random.nextInt(2) == 0)
        {
            return DARK_TEXTURE;
        }
        else
        {
            return BRIGHT_TEXTURE;
        }
    }

    @Override
    public void update()
    {
        super.update();
        checkIfOutOfScreen(toCheckIndex);
    }
}
