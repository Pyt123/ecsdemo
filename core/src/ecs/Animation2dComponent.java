package ecs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation2dComponent extends Component implements Updatable, Drawable
{
    private Texture texture;
    private TextureRegion [] frames;

    private float timePerFrame;
    private float timeInFrame;
    private int currentFrameIndex;

    public Animation2dComponent(Texture texture, int frameCount, float timePerFrame)
    {
        this.texture = texture;
        this.timePerFrame = timePerFrame;

        int frameWidth = texture.getWidth()/frameCount;
        frames = new TextureRegion[frameCount];
        for(int i = 0; i < frameCount; i++)
        {
            frames[i] = new TextureRegion(texture, frameWidth*i, 0, frameWidth, texture.getHeight());
        }
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
        timeInFrame += Time.getScaledDt();
        if(timeInFrame > timePerFrame)
        {
            currentFrameIndex = (currentFrameIndex + 1) % frames.length;
            timeInFrame = 0f;
        }
    }

    @Override
    public void draw()
    {
        Render.getBatch().draw(frames[currentFrameIndex],
                parent.getTransform().getPosition().x, parent.getTransform().getPosition().y);
    }

    @Override
    public void dispose()
    {
        texture.dispose();
    }

    public int getFrameWidth()
    {
        return frames[currentFrameIndex].getRegionWidth();
    }

    public int getFrameHeight()
    {
        return frames[currentFrameIndex].getRegionHeight();
    }
}
