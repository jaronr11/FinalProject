package MazeGame.model.MovementStrategies;

import MazeGame.model.Direction;
import MazeGame.model.GameEntities.Character;
import MazeGame.model.Position;

public class FollowMove implements MovementStrategy {

    @Override
    public Direction move(Position startPos, Character player) {
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();

        int enemyX =  startPos.getX();
        int enemyY = startPos.getY();

        int dx = enemyX-playerX;
        int dy = enemyY-playerY;

        if (dx > 0) return Direction.LEFT;
        if (dy > 0) return Direction.UP;
        if (dx < 0) return Direction.RIGHT;
        if (dy < 0) return Direction.DOWN;
        return Direction.UP;
    }
}
