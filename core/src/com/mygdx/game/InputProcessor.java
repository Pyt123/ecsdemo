package com.mygdx.game;

class InputProcessor implements com.badlogic.gdx.InputProcessor
{
    private boolean wasTouched = false;
    private int xPos;
    private int yPos;

    private Player player;

    public InputProcessor(Player player)
    {
        this.player = player;
    }

    public boolean handleInput()
    {
        if(wasTouched)
        {
            player.jump();
            wasTouched = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode)
    {

        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        wasTouched = true;
        xPos = screenX;
        yPos = screenY;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
