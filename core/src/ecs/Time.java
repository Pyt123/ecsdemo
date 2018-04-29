package ecs;

public final class Time
{
    private static float timeScale = 1f;             // the time scale
    private static float deltaTime = 0f;             // time since last frame in seconds
    private static float scaledDeltaTime = 0f;       // time since last frame in seconds * timeScale
    private static int frameCount = 0;

    public static float getTimeScale()
    {
        return timeScale;
    }

    public static float getDt()
    {
        return deltaTime;
    }

    public static float getScaledDt()
    {
        return scaledDeltaTime;
    }

    public static void setTimeScale(float timeScale)
    {
        Time.timeScale = timeScale;
    }

    public static void setDeltaTimes(float deltaTime)   // set scaled and unscaled
    {
        Time.deltaTime = deltaTime;
        Time.scaledDeltaTime = deltaTime * timeScale;
    }

    public static void increaseFrameCounter()
    {
        frameCount++;
    }

    public static void resetFrameCounter()
    {
        frameCount = 0;
    }

    public static int getFrameCount()
    {
        return frameCount;
    }

    private Time(){ }
}
