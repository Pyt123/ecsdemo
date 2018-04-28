package ecs;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Entity
{
    private Map<Class, Component> components;
    protected List<Updatable> updatableComponents = new ArrayList<Updatable>();
    protected List<Drawable> drawableComponents = new ArrayList<Drawable>();

    public Entity()
    {
        components = new HashMap<Class, Component>();
    }

    public void start() throws NecessaryComponentNotAttachedException
    { }

    public void dispose(){ }

    public Component getComponent(Class theClass)
    {
        return components.get(theClass);
    }

    protected void attachComponent(Class theClass, Component component) throws NecessaryComponentNotAttachedException
    {
        components.put(theClass, component);
        if(component instanceof Updatable)
        {
            updatableComponents.add((Updatable)component);
        }
        if(component instanceof Drawable)
        {
            drawableComponents.add((Drawable) component);
        }
        component.attachToEntity(this);
    }

    public final void update()
    {
        for (int i=0; i < updatableComponents.size(); i++)
        {
            updatableComponents.get(i).update();
        }
    }

    public final void draw()
    {
        for (int i = 0; i < drawableComponents.size(); i++)
        {
            drawableComponents.get(i).draw();
        }
    }
}
