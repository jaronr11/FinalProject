package MazeGame.model.MovementStrategies;

import MazeGame.model.Direction;
import MazeGame.model.GameEntities.Character;
import MazeGame.model.Position;

import java.util.Random;

public class RandomMoveStrategy implements MovementStrategy {
    @Override
    public Direction move(Position startPos, Character player) {
        Random random = new Random();
        int randMove = random.nextInt(4);
        return switch (randMove) {
            case 0 -> Direction.UP;
            case 1 -> Direction.DOWN;
            case 2 -> Direction.RIGHT;
            case 3 -> Direction.LEFT;
            default -> null;
        };
    }
}
