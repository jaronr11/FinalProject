package MazeGame.model.GameEntities;

import MazeGame.model.Position;

public class SmokeEffect {
    private final Position position;
    private int frameIndex = 0;
    private boolean finished = false;

    public SmokeEffect(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
    public int getFrameIndex() {
        return frameIndex;
    }
    public boolean isFinished() {
        return finished;
    }
    public void nextFrame(int maxFrames) {
        frameIndex++;
        if (frameIndex >= maxFrames) {
            finished = true;
        }
    }
}
