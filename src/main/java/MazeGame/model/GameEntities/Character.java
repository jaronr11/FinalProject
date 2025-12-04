package MazeGame.model.GameEntities;

import MazeGame.model.Direction;
import MazeGame.model.Position;
import MazeGame.model.Room;

public abstract class Character {
    protected double health;
    protected Position position;
    protected Direction lastDirection = Direction.DOWN;
    protected ProjectileFactory.ProjectileType projectileType;
    protected final int MOVE_DISTANCE = 1;

    public void loseHealth(double health) {
        this.health -= health;
    }
    public void gainHealth(double health) {
        this.health += health;
    }

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
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
            case UP -> new Position(x, y - MOVE_DISTANCE);
            case DOWN -> new Position(x, y + MOVE_DISTANCE);
            case LEFT -> new Position(x - MOVE_DISTANCE, y);
            case RIGHT -> new Position(x + MOVE_DISTANCE, y);
            case NONE -> new Position(x, y);
        };
    }
    public void move(Direction direction, Room currentRoom) {
        this.lastDirection = direction;
        Position newPosition = getNewPosition(direction);
        if (currentRoom.isWalkable(newPosition)) {
            this.position = newPosition;
        }
    }

    public abstract void doAction(Room room, Character character);

    public void setProjectileType(ProjectileFactory.ProjectileType projectileType) {
        this.projectileType = projectileType;
    }

    public ProjectileFactory.ProjectileType getProjectileType() {
        return projectileType;
    }

}
