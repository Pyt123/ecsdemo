package ecs;

import com.badlogic.gdx.graphics.Camera;
import com.mygdx.game.Game;

public class CameraComponent extends Component implements Updatable
{
    private Camera camera;

    public CameraComponent(Camera camera)
    {
        this.camera = camera;
        camera.translate(Game.VIRTUAL_WIDTH/2, Game.VIRTUAL_HEIGHT/2, 0);
    }

    @Override
    public void awake()
    {
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
