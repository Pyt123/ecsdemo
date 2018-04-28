package ecs;

import com.badlogic.gdx.math.Vector2;

public class Move2dComponent extends Component implements Updatable
{
    private final float VELOCITY_MARGIN = 0.001f;
    private Vector2 velocity = new Vector2();
    private Position2dComponent position2dComponent;

    public Move2dComponent(int xVel, int yVel)
    {
        initialize(xVel, yVel);
    }

    public Move2dComponent initialize(int xVel, int yVel)
    {
        setVelocity(xVel, yVel);
        return this;
    }

    public void setVelocity(int xVel, int yVel)
    {
        velocity.set(xVel, yVel);
    }

    public Vector2 getVelocity()
    {
        return velocity;
    }

    @Override
    protected void attachToEntity(Entity parentEntity)
    {
        super.attachToEntity(parentEntity);
        position2dComponent = (Position2dComponent)parentEntity.getComponent(Position2dComponent.class);
    }

    @Override
    public void update()
    {
        if(!velocity.isZero(VELOCITY_MARGIN))
        {
            position2dComponent.setPosition(
                    position2dComponent.getPosition().x + Time.getScaledDt() * velocity.x,
                    position2dComponent.getPosition().y + Time.getScaledDt() * velocity.y);
            position2dComponent.setChanged(true);
        }
        else
        {
            position2dComponent.setChanged(false);
        }
    }
}
