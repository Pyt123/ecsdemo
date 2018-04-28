package ecs;

import com.badlogic.gdx.math.Rectangle;

public class BoxCollider2dComponent extends Component implements Updatable
{
    private Rectangle bounds;
    private SpriteComponent spriteComponent;

    public BoxCollider2dComponent(SpriteComponent spriteComponent)
    {
        this.spriteComponent = spriteComponent;
    }

    @Override
    public void start()
    {
        bounds = new Rectangle(parent.getTransform().getPosition().x, parent.getTransform().getPosition().y,
                spriteComponent.getSprite().getWidth(), spriteComponent.getSprite().getHeight());
    }

    @Override
    public void awake()
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

    public Rectangle getBounds()
    {
        return bounds;
    }
}
