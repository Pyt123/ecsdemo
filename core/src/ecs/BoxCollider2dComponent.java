package ecs;

import com.badlogic.gdx.math.Rectangle;

public class BoxCollider2dComponent extends Component implements Updatable
{
    private Rectangle bounds;
    Position2dComponent position2dComponent;

    public BoxCollider2dComponent(int startX, int startY, int width, int height)
    {
        initialize(startX, startY, width, height);
    }

    public BoxCollider2dComponent initialize(int startX, int startY, int width, int height)
    {
        bounds = new Rectangle(startX, startY, width, height);
        return this;
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
        if(position2dComponent.isChanged())
        {
            bounds.setPosition(position2dComponent.getPosition().x, position2dComponent.getPosition().y);
        }
    }
}
