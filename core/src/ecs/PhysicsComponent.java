package ecs;

public class PhysicsComponent extends Component implements Updatable
{
    private final float TRESHHOLD = 0.1f;
    private final float JUMP_FORCE = 1500f;

    private Velocity2dComponent velocityC;
    private float timeInAir = 0f;
    private float groundLevel;
    private boolean wasGrounded;
    private boolean isGrounded;

    public PhysicsComponent(Velocity2dComponent velocityC, float groundLevel)
    {
        this.velocityC = velocityC;
        this.groundLevel = groundLevel;
    }

    @Override
    void awake()
    {
    }

    @Override
    void start()
    {
    }

    @Override
    public void update()
    {
        isGrounded = checkGrounded();
        if(!isGrounded)
        {
            applyGravity();
            timeInAir += Time.getScaledDt();
            wasGrounded = false;
        }
        else
        {
            if(!wasGrounded)
            {
                velocityC.getVelocity().y = 0f;
                wasGrounded = true;
                timeInAir = 0f;
            }
        }
    }

    private boolean checkGrounded()
    {
        return parent.getTransform().getPosition().y <= groundLevel + TRESHHOLD;
    }

    private void applyGravity()
    {
        velocityC.getVelocity().y -= Physics.getGravityY() * timeInAir * Time.getScaledDt();
    }

    public void jump()
    {
        if(isGrounded)
        {
            velocityC.setVelocity(velocityC.getVelocity().x, velocityC.getVelocity().y + JUMP_FORCE);
        }
    }
}
