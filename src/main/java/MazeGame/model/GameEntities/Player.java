package MazeGame.model.GameEntities;

import MazeGame.model.Direction;
import MazeGame.model.Position;

import java.util.List;

public class Player extends Character {
    private List<Artifact> inventory;
    private Direction lastDirection;

    public Player() {
        this.position = new Position(0, 0);
        this.health = 10.0;
    }

    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }
}
