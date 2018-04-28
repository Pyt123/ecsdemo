package ecs;

import com.badlogic.gdx.math.Vector2;

public class Velocity2dComponent extends Component implements Updatable
{
    private final float VELOCITY_MARGIN = 0.001f;
    private Vector2 velocity = new Vector2();

    public Velocity2dComponent(float xVel, float yVel)
    {
        initialize(xVel, yVel);
    }

    public Velocity2dComponent initialize(float xVel, float yVel)
    {
        setVelocity(xVel, yVel);
        return this;
    }

    public void setVelocity(float xVel, float yVel)
    {
        velocity.set(xVel, yVel);
    }

    public Vector2 getVelocity()
    {
        return velocity;
    }

    @Override
    public void start()
    {

    }

    @Override
    public void awake()
    {

    }

    @Override
    public void update()
    {
        if(!velocity.isZero(VELOCITY_MARGIN))
        {
            parent.getTransform().setPosition(
                    parent.getTransform().getPosition().x + Time.getScaledDt() * velocity.x,
                    parent.getTransform().getPosition().y + Time.getScaledDt() * velocity.y);
        }
        else
        {
            parent.getTransform().setNoPositionChanged();
        }
    }
}
