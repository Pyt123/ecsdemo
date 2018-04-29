package ecs;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PhysicsComponent extends Component implements Updatable
{
    private final float L_TRESHHOLD = 0.5f;
    private final float B_TRESHHOLD = 10f;
    private final float JUMP_FORCE = 1500f;

    private Velocity2dComponent velocityC;
    private float timeInAir = 0f;
    private boolean isGrounded = true;
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

        if(collidedWith != null && velocityC.getVelocity().y < L_TRESHHOLD)
        {
            Vector2 midPos = new Vector2(collider.getBounds().x + collider.getBounds().width/4,
                    collider.getBounds().y + collider.getBounds().height/2);
            float secX = midPos.x + collider.getBounds().width/2;
            float upYcollidedWith = collidedWith.y + collidedWith.height;
            if(secX > collidedWith.x && midPos.x < collidedWith.x + collidedWith.width &&
                    midPos.y > upYcollidedWith)
            {
                parent.getTransform().setPosition(parent.getTransform().getPosition().x, upYcollidedWith-L_TRESHHOLD);
                isGrounded = true;
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

    public void jump()
    {
        if(isGrounded)
        {
            velocityC.setVelocity(velocityC.getVelocity().x, velocityC.getVelocity().y + JUMP_FORCE);
        }
    }
}
