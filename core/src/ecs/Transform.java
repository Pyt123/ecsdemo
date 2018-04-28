package ecs;

import com.badlogic.gdx.math.Vector2;

public final class Transform
{
    private Vector2 position = new Vector2();
    private boolean isPosChanged = false;

    public Transform()
    {
    }

    public final Vector2 getPosition()
    {
        return position;
    }

    public boolean isPosChanged()
    {
        return isPosChanged;
    }

    public void setPosition(float x, float y)
    {
        position.set(x, y);
        isPosChanged = true;
    }

    public void setNoPositionChanged()
    {
        isPosChanged = false;
    }
}
