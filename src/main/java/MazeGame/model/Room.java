package MazeGame.model;

import MazeGame.model.GameEntities.Artifact;
import MazeGame.model.GameEntities.Character;
import MazeGame.model.GameEntities.Enemy;
import MazeGame.model.GameEntities.Projectile;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<Artifact> artifacts;
    private final int MAP_HEIGHT = 9;
    private final int MAP_WIDTH = 15;
    private final Tile[][] tiles = new Tile[MAP_HEIGHT][MAP_WIDTH];
    private final List<Enemy> enemies = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    public Room() {
        for (int y=0; y<MAP_HEIGHT; y++) {
            for (int x=0; x<MAP_WIDTH; x++) {
                tiles[y][x] = new Tile(null, true);
            }
        }
        tiles[0][1] = new Tile(null, false);
    }
    public boolean isWalkable(Position position) {
        int x = position.x;
        int y = position.y;
        if (x < 0 || x >= MAP_WIDTH || y < 0 || y >= MAP_HEIGHT) {
            return false;
        }
        return tiles[y][x].isWalkable();
    }

    public int getMAP_HEIGHT() {
        return MAP_HEIGHT;
    }
    public int getMAP_WIDTH() {
        return MAP_WIDTH;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public void addProjectile(Projectile projectile) {
        this.projectiles.add(projectile);
    }
}
