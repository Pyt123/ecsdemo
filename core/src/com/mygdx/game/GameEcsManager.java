package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import ecs.Audio2dComponent;
import ecs.EcsManager;
import ecs.Entity;

public class GameEcsManager extends EcsManager
{
    private GameplayInputProcessor inputProcessor;

    public GameEcsManager(Camera camera, Viewport viewport)
    {
        super(camera, viewport);
        Music music = Gdx.audio.newMusic(Gdx.files.internal("soundtrack.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();
        AudioResources.initAudioResources();
    }

    @Override
    public void start()
    {
        super.start();
        Player player = new Player(0, 250);
        FollowingCamera fCamera =  new FollowingCamera(camera, viewport, player, new Vector2(800, 0));
        Background background = new Background(-140, 0, fCamera);

        inputProcessor = new GameplayInputProcessor(player, background);
        Gdx.input.setInputProcessor(inputProcessor);

        PlatformGenerator platformGenerator = new PlatformGenerator(200, 50, player, fCamera);
        inputProcessor.setPlatformGenerator(platformGenerator);

        addAndStart(background);
        addAndStart(platformGenerator);
        addAndStart(player);
        addAndStart(fCamera);
        addAndStart(new TimeScaleManipulator(0, 0));
    }

    protected void handleInput()
    {

    }

    @Override
    public void restart()
    {
        dispose();
        start();
    }
}