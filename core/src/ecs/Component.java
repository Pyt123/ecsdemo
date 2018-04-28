package ecs;

public abstract class Component
{
    protected boolean isEnabled = true;
    protected boolean toDestroy = false;
    protected Entity parentEntity;

    protected void attachToEntity(Entity parentEntity)
    {
        this.parentEntity = parentEntity;
    }

    public void setEnabled(boolean isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    public void destroy()
    {
        toDestroy = true;
    }
}
