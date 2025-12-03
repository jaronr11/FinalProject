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
    private int speed = 2;
    private double damage = 1;
    private double size;
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

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage *= damage;
    }

    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void move() {
        x+=dx *speed;
        y+=dy* speed;
    }
}
