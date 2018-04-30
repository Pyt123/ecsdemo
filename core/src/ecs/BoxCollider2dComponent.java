package ecs;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class BoxCollider2dComponent extends Component implements Updatable
{
    private Rectangle bounds;
    private boolean isActive = true;
    private List<BoxCollider2dComponent> colliders = new ArrayList<BoxCollider2dComponent>();

    public BoxCollider2dComponent(SpriteComponent spriteComponent, Entity parent)
    {
        bounds = new Rectangle(parent.getTransform().getPosition().x, parent.getTransform().getPosition().y,
                spriteComponent.getSprite().getWidth(), spriteComponent.getSprite().getHeight());
    }

    public BoxCollider2dComponent(int width, int height, Entity parent)
    {
        bounds = new Rectangle(parent.getTransform().getPosition().x, parent.getTransform().getPosition().y, width, height);
    }

    @Override
    public void awake()
    {
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
            bounds.setPosition(parent.getTransform().getPosition().x, parent.getTransform().getPosition().y);
        }
    }

    public Rectangle checkForCollisions()
    {
        Rectangle toCheck;
        BoxCollider2dComponent toCheckCollider;
        for(int i = colliders.size()-1; i >= 0; i--)
        {
            toCheckCollider = colliders.get(i);
            if(toCheckCollider.isActive)
            {
                toCheck = toCheckCollider.getBounds();
                if(bounds.overlaps(toCheck))
                {
                    return toCheck;
                }
            }
        }
        return null;
    }

    public void addColliderWhichCanCollideWith(BoxCollider2dComponent collider)
    {
        colliders.add(collider);
    }

    public void addColliderWhichCanCollideWith(List<BoxCollider2dComponent> colliders)
    {
        this.colliders.addAll(colliders);
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void changeActive()
    {
        isActive = !isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }
}
