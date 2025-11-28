package MazeGame.model.GameEntities;

import MazeGame.model.Direction;
import MazeGame.model.Maze;
import MazeGame.model.Position;
import MazeGame.model.RandomMoveStrategy;

public class Enemy extends Character {
    private double health;
    MovementStrategy strategy;

    private double DEFAULT_ENEMY_HEALTH = 5.0;

    public Enemy() {
        this.strategy = new RandomMoveStrategy();
        this.position = new Position(0,0);
        this.health = DEFAULT_ENEMY_HEALTH;
    }
    public Enemy(MovementStrategy strategy, Position position, double health) {
        this.strategy = strategy;
        this.position = position;
        this.health = health;
    }

    public void doAction(Maze maze) {
        Direction move = strategy.move();
        maze.moveCharacter(this, move);
        Direction playerTargetDir = maze.getDirectionTowardPlayer(this);
        maze.spawnEnemyProjectile(this, playerTargetDir);
    }

}
