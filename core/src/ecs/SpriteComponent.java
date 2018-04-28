package ecs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent extends Component implements Updatable, Drawable
{
    private Sprite sprite;
    Position2dComponent position2dComponent;

    public SpriteComponent(Texture texture, int startX, int startY)
    {
        sprite = new Sprite(texture, startX, startY, texture.getWidth(), texture.getHeight());
    }

    public SpriteComponent(Texture texture, int startX, int startY, int width, int height)
    {
        sprite = new Sprite(texture, startX, startY, width, height);
    }

    public Sprite getSprite()
    {
        return sprite;
    }

    @Override
    protected void attachToEntity(Entity parentEntity)
    {
        super.attachToEntity(parentEntity);
        position2dComponent = (Position2dComponent)parentEntity.getComponent(Position2dComponent.class);
    }

    public void update()
    {
        if(position2dComponent != null && position2dComponent.isChanged())
        {
            sprite.setPosition(position2dComponent.getPosition().x, position2dComponent.getPosition().y);
        }
    }

    @Override
    public void draw()
    {
        sprite.draw(Render.getBatch());
    }
}
