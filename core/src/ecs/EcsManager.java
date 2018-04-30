package ecs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

public abstract class EcsManager
{
    protected ApplicationAdapter context;
    protected Camera camera;
    protected Viewport viewport;

    private List<Entity> entities;

    public EcsManager(ApplicationAdapter context, Batch batch, Camera camera, Viewport viewport)
    {
        this.context = context;
        Render.setBatch(batch);
        this.camera = camera;
        this.viewport = viewport;
    }

    public final void addAndStart(Entity entity)
    {
        entities.add(entity);
        entity.start();
    }

    public void start()
    {
        this.entities = new ArrayList<Entity>(15);;
        Time.resetFrameCounter();

        Scene.startScene(entities, this);
    }

    public void nextFrame()
    {
        if(Time.getFrameCount() > 0)
        {
            handleInput();
            update();
        }
        draw();
    }

    public void dispose()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).dispose();
        }
    }

    protected abstract void handleInput();

    private void update()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).update();
        }
    }

    private void draw()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).draw();
        }
    }

    public abstract void restart();
}
