package MazeGame.model.GameEntities;

import MazeGame.model.Position;

import java.util.List;

public class Player extends Character {
    private double health;
    private List<Artifact> inventory;

    public Player() {
        this.position = new Position(0, 0);
    }
}
