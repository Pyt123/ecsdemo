package ecs;

import com.badlogic.gdx.math.Vector2;

public class Position2dComponent extends Component
{
    private Vector2 position = new Vector2();
    private boolean isChanged = false;

    public Position2dComponent(float xPos, float yPos)
    {
        initialize(xPos, yPos);
    }

    public Position2dComponent initialize(float xVel, float yVel)
    {
        setPosition(xVel, yVel);
        return this;
    }

    public void setPosition(float xPos, float yPos)
    {
        position.set(xPos, yPos);
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setChanged(boolean isChanged)
    {
        this.isChanged = isChanged;
    }

    public boolean isChanged()
    {
        return isChanged;
    }
}
