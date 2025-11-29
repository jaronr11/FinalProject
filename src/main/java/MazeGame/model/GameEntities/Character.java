package MazeGame.model.GameEntities;

import MazeGame.model.Direction;
import MazeGame.model.Position;
import MazeGame.model.Room;

public abstract class Character {
    protected double health;
    protected Position position;
    protected Direction lastDirection = Direction.DOWN;

    public void loseHealth(double health) {
        this.health -= health;
    }
    public void gainHealth(double health) {
        this.health += health;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isAlive() {
        return health >0;
    }

    public double getHealth() {
        return health;
    }

    Position getNewPosition(Direction direction) {
        Position currentPos = this.getPosition();
        int x =  currentPos.getX();
        int y =  currentPos.getY();
        return switch (direction) {
            case UP -> new Position(x, y - 1);
            case DOWN -> new Position(x, y + 1);
            case LEFT -> new Position(x - 1, y);
            case RIGHT -> new Position(x + 1, y);
        };
    }
    public void move(Direction direction, Room currentRoom) {
        this.lastDirection = direction;
        Position newPosition = getNewPosition(direction);
        if (currentRoom.isWalkable(newPosition)) {
            this.position = newPosition;
        }
    }

    public abstract void doAction(Room room, Character player);

    public Direction getLastDirection() {
        return lastDirection;
    }




}
