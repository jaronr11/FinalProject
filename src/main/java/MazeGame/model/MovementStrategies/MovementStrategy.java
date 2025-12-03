package MazeGame.model.MovementStrategies;

import MazeGame.model.Direction;
import MazeGame.model.GameEntities.Character;
import MazeGame.model.Position;

public interface MovementStrategy {
    public Direction move(Position startPos, Character player);
}
