package ecs;

public abstract class Component
{
    protected boolean isEnabled = true;
    protected boolean toDestroy = false;
    protected Entity parent;

    protected final void attachToEntity(Entity parentEntity)
    {
        this.parent = parentEntity;
    }

    abstract void awake();

    abstract void start();

    public final void setEnabled(boolean isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    public final void destroy()
    {
        toDestroy = true;
    }
}
