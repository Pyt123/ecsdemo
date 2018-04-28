package com.mygdx.game;

class GameStateManager
{
    private static final GameStateManager instance = new GameStateManager();

    static GameStateManager getInstance()
    {
        return instance;
    }

    private GameState actualState;

    private GameStateManager()
    {
    }

    public void changeState(GameState newState)
    {
        actualState.exitState();
        actualState = newState;
        newState.startState();
    }
}
