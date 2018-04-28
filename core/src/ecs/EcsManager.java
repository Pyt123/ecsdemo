package ecs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;

public abstract class EcsManager
{
    private ApplicationAdapter context;
    private InputProcessor inputProcessor;

    protected List<Entity> entities = new ArrayList<Entity>(10);

    public EcsManager(ApplicationAdapter context, InputProcessor inputProcessor, Batch batch)
    {
        this.context = context;
        this.inputProcessor = inputProcessor;
        Render.setBatch(batch);
    }

    public abstract void start();

    public void nextFrame()
    {
        handleInput();
        update();
        draw();
    }

    public void dispose()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).dispose();
        }
    }

    private void handleInput()
    {
    }

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
}
