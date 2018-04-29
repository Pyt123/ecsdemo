package ecs;

public class RestartComponent extends Component
{
    private EcsManager ecsManager;

    public RestartComponent(EcsManager ecsManager)
    {
        this.ecsManager = ecsManager;
    }

    @Override
    void awake()
    {
    }

    @Override
    void start()
    {
    }

    public void restart()
    {
        ecsManager.restart();
    }
}
