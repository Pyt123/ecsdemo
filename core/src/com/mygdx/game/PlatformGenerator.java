package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import ecs.BoxCollider2dComponent;
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
        InputProcessor inputProcessor = (InputProcessor)Gdx.input.getInputProcessor();
        platforms[0] = new Platform(xPos, yPos, DARK_TEXTURE, player, (inputProcessor.getLightState() == InputProcessor.LightState.DARK));
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

        Texture texture;
        boolean colliderActive;
        InputProcessor inputProcessor = (InputProcessor)Gdx.input.getInputProcessor();
        if(random.nextInt(2) == 0) { texture = DARK_TEXTURE; colliderActive = (inputProcessor.getLightState() == InputProcessor.LightState.DARK);}
        else { texture = BRIGHT_TEXTURE; colliderActive = (inputProcessor.getLightState() == InputProcessor.LightState.BRIGHT);}

        platforms[index] = new Platform(posX, getTransform().getPosition().y, texture, player, colliderActive);
        return platforms[index];
    }

    private void repositionPlatform(int index)
    {
        int prevIndex = (platforms.length + index -1)%platforms.length;
        float prevPosX = platforms[prevIndex].getTransform().getPosition().x;

        float posX = prevPosX + MIN_SPACE + random.nextInt(MAX_SPACE - MIN_SPACE);

        Texture texture;
        boolean colliderActive;
        InputProcessor inputProcessor = (InputProcessor)Gdx.input.getInputProcessor();
        if(random.nextInt(2) == 0) { texture = DARK_TEXTURE; colliderActive = (inputProcessor.getLightState() == InputProcessor.LightState.DARK);}
        else { texture = BRIGHT_TEXTURE; colliderActive = (inputProcessor.getLightState() == InputProcessor.LightState.BRIGHT);}

        platforms[index].getTransform().setPosition(posX, getTransform().getPosition().y);
        platforms[index].changeTexture(texture, true);
        ((BoxCollider2dComponent)(platforms[index].getComponent(BoxCollider2dComponent.class))).setActive(colliderActive);
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

    @Override
    public void update()
    {
        super.update();
        checkIfOutOfScreen(toCheckIndex);
    }

    public void changePlatformsState()
    {
        for(int i = 0; i < platforms.length; i++)
        {
            ((BoxCollider2dComponent)(platforms[i].getComponent(BoxCollider2dComponent.class))).changeActive();
        }
    }
}
