package ecs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent extends Component implements Updatable, Drawable
{
    private Sprite sprite;
    private Texture texture;
    private int width;
    private int height;

    public SpriteComponent(Texture texture)
    {
        this.texture = texture;
        width = texture.getWidth();
        height = texture.getHeight();
        sprite = new Sprite(texture, 0,0, width, height);
    }

    public SpriteComponent(Texture texture, int width, int height)
    {
        this.texture = texture;
        this.width = width;
        this.height = height;
        sprite = new Sprite(texture, 0,0, width, height);
    }

    @Override
    public void awake()
    {
    }

    @Override
    public void start()
    {
    }

    public Sprite getSprite()
    {
        return sprite;
    }

    public void update()
    {
        if(parent.getTransform().isPosChanged())
        {
            sprite.setPosition(parent.getTransform().getPosition().x, parent.getTransform().getPosition().y);
        }
    }

    @Override
    public void draw()
    {
        sprite.draw(Render.getBatch());
    }

    @Override
    public void dispose()
    {
        texture.dispose();
    }
}
