package MazeGame.model.MovementStrategies;

import MazeGame.model.Direction;
import MazeGame.model.Position;

public class NoMoveStrategy implements MovementStrategy {

    @Override
    public Direction move(Position startPos, MazeGame.model.GameEntities.Character player) {
        return Direction.NONE;
    }
}
