package MazeGame.model.MovementStrategies;

import MazeGame.model.Direction;
import MazeGame.model.GameEntities.Character;
import MazeGame.model.Position;

public interface MovementStrategy {
    Direction move(Position startPos, Character player);
}
