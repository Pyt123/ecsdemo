package ecs;

import com.badlogic.gdx.graphics.g2d.Batch;

public final class Render
{
    private static Batch batch;

    public static Batch getBatch()
    {
        return batch;
    }

    public static void setBatch(Batch batch)
    {
        Render.batch = batch;
    }

    private Render() { }
}
