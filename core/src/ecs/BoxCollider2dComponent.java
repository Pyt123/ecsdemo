package ecs;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class BoxCollider2dComponent extends Component implements Updatable
{
    private Rectangle bounds;
    private SpriteComponent spriteComponent;
    private List<BoxCollider2dComponent> colliders = new ArrayList<BoxCollider2dComponent>();

    public BoxCollider2dComponent(SpriteComponent spriteComponent)
    {
        this.spriteComponent = spriteComponent;
    }

    @Override
    public void awake()
    {
    }

    @Override
    public void start()
    {
        bounds = new Rectangle(parent.getTransform().getPosition().x, parent.getTransform().getPosition().y,
                spriteComponent.getSprite().getWidth(), spriteComponent.getSprite().getHeight());
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
        for(int i = colliders.size()-1; i >= 0; i--)
        {
            toCheck = colliders.get(i).getBounds();
            if(bounds.overlaps(toCheck))
            {
                return toCheck;
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
}
