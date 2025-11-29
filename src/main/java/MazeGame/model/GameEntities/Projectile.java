package MazeGame.model.GameEntities;

import MazeGame.model.Direction;
import MazeGame.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Projectile {
    private double x;
    private double y;
    private double dx;
    private double dy;
    private int speed = 1;
    private int damage = 1;
    private ProjectileOwner owner;


    public Projectile(Position startPos, Position targetPos, ProjectileOwner owner) {
        this.x = startPos.getX();
        this.y = startPos.getY();
        this.owner = owner;

        double xDiff = targetPos.getX() - startPos.getX();
        double yDiff = targetPos.getY() - startPos.getY();

        double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

        if (distance != 0) {
            this.dx = xDiff / distance;
            this.dy = yDiff / distance;
        }
        else {
            this.dx = 0;
            this.dy = 0;
        }
    }


    public int getSpeed() {
        return speed;
    }

    public Position getPosition() {
        return new Position((int)x, (int)y);
    }

    public ProjectileOwner getOwner() {
        return owner;
    }

    public int getDamage() {
        return damage;
    }

    public void move() {
        x+=dx;
        y+=dy;
    }
}
