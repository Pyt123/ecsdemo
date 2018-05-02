package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.Random;

public final class AudioResources
{
    private Sound [] explosionSounds;
    private Sound [] shotSounds;
    private Random random;

    private static AudioResources instance;

    public static void initAudioResources()
    {
        instance = new AudioResources();
        instance.random = new Random();
        instance.explosionSounds = new Sound[2];
        instance.explosionSounds[0] = Gdx.audio.newSound(Gdx.files.internal("explosion1.wav"));
        instance.explosionSounds[1] = Gdx.audio.newSound(Gdx.files.internal("explosion2.wav"));

        instance.shotSounds = new Sound[1];
        instance.shotSounds[0] = Gdx.audio.newSound(Gdx.files.internal("shots.wav"));
    }

    private AudioResources() { }

    public static void playExplosion()
    {
        instance.explosionSounds[instance.random.nextInt(instance.explosionSounds.length)].play();
    }

    public static void playShot()
    {
        instance.shotSounds[instance.random.nextInt(instance.shotSounds.length)].play();
    }
}
