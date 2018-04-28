package ecs;

import com.badlogic.gdx.math.Vector2;

public class FollowingBehaviourComponent extends Component implements Updatable
{
    private Entity toFollow;
    private Vector2 followedPosition;
    private Vector2 offset;
    private boolean followX;
    private boolean followY;

    public FollowingBehaviourComponent(Entity entityToFollow, Vector2 offset, boolean inX, boolean inY)
    {
        setTarget(entityToFollow);
        setOffset(offset);
        followX = inX;
        followY = inY;
    }

    @Override
    public void awake()
    {
        followedPosition = toFollow.getTransform().getPosition();
    }

    @Override
    public void start()
    {

    }

    @Override
    public void update()
    {
        if(parent.getTransform().isPosChanged())
        {
            if(followX)
            {
                if(followY)
                {
                    parent.transform.setPosition(followedPosition.x + offset.x, followedPosition.y + offset.y);
                }
                else
                {
                    parent.transform.setPosition(followedPosition.x + offset.x, parent.getTransform().getPosition().y);
                }
            }
            else if (followY)
            {
                parent.transform.setPosition(parent.getTransform().getPosition().x, followedPosition.y + offset.y);
            }
        }
    }

    public void setTarget(Entity entity)
    {
        toFollow = entity;
    }

    public void setOffset(Vector2 offset)
    {
        this.offset = offset;
    }
}
