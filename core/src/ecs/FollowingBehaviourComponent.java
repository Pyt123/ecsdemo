package ecs;

import com.badlogic.gdx.math.Vector2;

public class FollowingBehaviourComponent extends Component implements Updatable
{
    private Entity toFollow;
    private Vector2 followedPosition;
    private Vector2 offset;

    public FollowingBehaviourComponent(Entity entityToFollow, Vector2 offset)
    {
        setTarget(entityToFollow);
        setOffset(offset);
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
            parent.transform.setPosition(followedPosition.x + offset.x, followedPosition.y + offset.y);
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
