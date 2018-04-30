package ecs;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PhysicsComponent extends Component implements Updatable
{
    private final float L_TRESHHOLD = 15f;

    private final float VEL_Y_TO_VEL_X_NEEDED = 5;

    private Velocity2dComponent velocityC;
    private float timeInAir = 0f;
    private boolean isGrounded;
    private BoxCollider2dComponent collider;

    public PhysicsComponent(Velocity2dComponent velocityC, BoxCollider2dComponent collider)
    {
        this.velocityC = velocityC;
        this.collider = collider;
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
        Rectangle collidedWith = collider.checkForCollisions();

        if(collidedWith != null && velocityC.getVelocity().y <= 0.001)
        {
            Vector2 midPos = new Vector2(collider.getBounds().x + collider.getBounds().width/5,
                    collider.getBounds().y + collider.getBounds().height/5);
            float secX = midPos.x + collider.getBounds().width/2.5f;
            float upYcollidedWith = collidedWith.y + collidedWith.height;
            if(secX > collidedWith.x && midPos.x < collidedWith.x + collidedWith.width)
            {
                float velYtoVelX = Math.abs(velocityC.getVelocity().y) / Math.abs(velocityC.getVelocity().x);
                if(midPos.y > upYcollidedWith || velYtoVelX > VEL_Y_TO_VEL_X_NEEDED)
                {
                    parent.getTransform().setPosition(parent.getTransform().getPosition().x, upYcollidedWith-L_TRESHHOLD);
                    isGrounded = true;
                }
            }
            else
            {
                isGrounded = false;
            }
        }
        else
        {
            isGrounded = false;
        }

        handleGravitation();
    }

    private void handleGravitation()
    {
        if(isGrounded)
        {
            velocityC.getVelocity().y = 0f;
            timeInAir = 0f;
        }
        else
        {
            velocityC.getVelocity().y -= Physics.getGravityY() * timeInAir * Time.getScaledDt();
            timeInAir += Time.getScaledDt();
        }
    }

    public void addForce(Vector2 force)
    {
        velocityC.setVelocity(velocityC.getVelocity().x + force.x, velocityC.getVelocity().y + force.y);
    }

    public boolean getIsGrounded()
    {
        return isGrounded;
    }
}
