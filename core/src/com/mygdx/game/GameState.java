package com.mygdx.game;

public interface GameState
{
    public abstract void startState();
    public abstract void handleStateInput();
    public abstract void updateState();
    public abstract void exitState();
}
