package MazeGame.model.GameEntities;

import MazeGame.model.Position;

public abstract class Character {
    protected double health;
    protected double damage;
    protected Position position;

    protected void loseHealth(double health) {
        this.health -= health;
    }
    protected void gainHealth(double health) {
        this.health += health;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) { this.position = position; }


    public double getHealth() {
        return health;
    }
    public double getDamage() {
        return damage;
    }

    public void attack() {
        System.out.println(damage);
    }

}
