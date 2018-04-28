package ecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity
{
    private Map<Class, Component> components;
    protected List<Updatable> updatableComponents = new ArrayList<Updatable>();
    protected List<Drawable> drawableComponents = new ArrayList<Drawable>();

    protected String name;
    protected Transform transform = new Transform();


    public Entity(float xPos, float yPos)
    {
        components = new HashMap<Class, Component>();
        transform.setPosition(xPos, yPos);
    }

    public void dispose()
    {
        for(int i=0; i < drawableComponents.size(); i++)
        {
            drawableComponents.get(i).dispose();
        }
    }

    public final Component getComponent(Class theClass)
    {
        return components.get(theClass);
    }

    public final void attachComponent(Component component)
    {
        components.put(component.getClass(), component);
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

    public final void start()
    {
        Object [] comps = (components.values().toArray());
        Component component;
        for(int  i=0; i < comps.length; i++)
        {
            component = (Component)comps[i];
            component.awake();
        }
        for(int  i=0; i< comps.length; i++)
        {
            component = (Component)comps[i];
            component.start();
        }
    }

    public final void update()
    {
        for (int i=0; i < updatableComponents.size(); i++)
        {
            updatableComponents.get(i).update();
        }
    }

    public void draw()
    {
        for (int i = 0; i < drawableComponents.size(); i++)
        {
            drawableComponents.get(i).draw();
        }
    }

    public Transform getTransform()
    {
        return transform;
    }
}
