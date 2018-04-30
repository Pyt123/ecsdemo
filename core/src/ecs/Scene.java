package ecs;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

public final class Scene
{
    private static List<Entity> entities;
    private static EcsManager ecsManager;
    private static Camera camera;
    private static Viewport viewport;

    public static void startScene(List<Entity> entities, EcsManager ecsManager)
    {
        Scene.entities = entities;
        Scene.ecsManager = ecsManager;
    }

    public static EcsManager getEcsManager()
    {
        return ecsManager;
    }

    public static void setupCameraAndViewport(Camera camera, Viewport viewport)
    {
        Scene.camera = camera;
        Scene.viewport = viewport;
        Scene.viewport.setCamera(camera);
    }

    public static Camera getCamera()
    {
        return camera;
    }

    public static Viewport getViewport()
    {
        return viewport;
    }
}
