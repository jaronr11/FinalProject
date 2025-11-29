package MazeGame.model.GameEntities;

import MazeGame.model.Direction;
import MazeGame.model.Position;
import MazeGame.model.Room;

import java.util.List;

public class Player extends Character {
    private List<Artifact> inventory;
    private Direction lastDirection;

    public Player(Position startPos, double initialHealth) {
        this.position = startPos;
        this.health = 10.0;
        this.lastDirection = Direction.DOWN;
    }

    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }


    @Override
    public void doAction(Room room, Character player) {
    }
}
