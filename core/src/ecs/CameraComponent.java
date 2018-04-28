package ecs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

public class CameraComponent extends Component implements Updatable
{
    private Camera camera;

    public CameraComponent(Camera camera)
    {
        this.camera = camera;
    }

    @Override
    public void awake()
    {
        camera.translate(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
    }

    @Override
    public void start()
    {

    }

    @Override
    public void update()
    {
        if(parent.getTransform().isPosChanged())
        {
            camera.position.set(parent.getTransform().getPosition().x, parent.getTransform().getPosition().y, 0);
        }
    }
}
