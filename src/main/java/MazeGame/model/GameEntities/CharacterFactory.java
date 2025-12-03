package MazeGame.model.GameEntities;

import MazeGame.model.MovementStrategies.MovementStrategy;
import MazeGame.model.Position;

public class CharacterFactory {
    public Player createPlayer(Position startPos, double health)
    {
        return new Player(startPos, health);
    }

    public Character createEnemy(MovementStrategy movementStrategy, Position startPos, double health) {
        return new Enemy(movementStrategy, startPos, health);
    }
}

