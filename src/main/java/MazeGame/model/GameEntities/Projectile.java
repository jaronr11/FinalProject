package MazeGame.model.GameEntities;

import MazeGame.model.Direction;
import MazeGame.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Projectile {
    private Position position;
    private Direction direction;
    private int speed = 1;
    private int damage = 1;
    private ProjectileOwner owner;


    public Projectile(Position startPos, Direction direction, ProjectileOwner owner) {
        this.position = new Position(startPos.getX(), startPos.getY());
        this.direction = direction;
        this.owner = owner;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ProjectileOwner getOwner() {
        return owner;
    }

    public int getDamage() {
        return damage;
    }

    public void move() {
        switch (direction) {
            case UP:
                position.setY(position.getY() - speed);
                break;
            case DOWN:
                position.setY(position.getY() + speed);
                break;
            case LEFT:
                position.setX(position.getX() - speed);
                break;
            case RIGHT:
                position.setX(position.getX() + speed);
                break;
        }
    }
}
