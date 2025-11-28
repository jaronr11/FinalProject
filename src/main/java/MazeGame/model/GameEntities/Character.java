package MazeGame.model.GameEntities;

import MazeGame.model.Position;

public abstract class Character {
    protected double health;
    protected Position position;

    public void loseHealth(double health) {
        this.health -= health;
    }
    public void gainHealth(double health) {
        this.health += health;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) { this.position = position; }

    public boolean isAlive() {
        return health >0;
    }

    public double getHealth() {
        return health;
    }

}
