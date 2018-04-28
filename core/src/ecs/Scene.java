package ecs;

import java.util.List;

public final class Scene
{
    private static List<Entity> entities;
    private static EcsManager ecsManager;

    public static void startScene(List<Entity> entities, EcsManager ecsManager)
    {
        Scene.entities = entities;
        Scene.ecsManager = ecsManager;
    }

    public static EcsManager getEcsManager()
    {
        return ecsManager;
    }
}
