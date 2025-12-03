package MazeGame.model;

import MazeGame.model.GameEntities.*;
import MazeGame.model.GameEntities.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private final int MAP_HEIGHT = 9;
    private final int MAP_WIDTH = 15;
    private final Tile[][] tiles = new Tile[MAP_HEIGHT][MAP_WIDTH];
    private final List<Character> enemies = new ArrayList<>();
    private final List<Projectile> projectiles = new ArrayList<>();
    private final List<Weapon> weapons = new ArrayList<>();
    private Door door;
    private Room nextLocation;

    public Room() {
        for (int y=0; y<MAP_HEIGHT; y++) {
            for (int x=0; x<MAP_WIDTH; x++) {
                tiles[y][x] = new Tile(null, true);
            }
        }
    }
    public Room(Position[] obstacles) {
        for (int y=0; y<MAP_HEIGHT; y++) {
            for (int x=0; x<MAP_WIDTH; x++) {
                    tiles[y][x] = new Tile(null, true);
                }
            }
        for (Position obstacle : obstacles) {
            tiles[obstacle.getY()][obstacle.getX()].setWalkable(false);
            }
        door = null;
    }

    void generateDoor() {
        Random rand = new Random();
        Position randomPosition;
        do {
            randomPosition = new Position(rand.nextInt(MAP_WIDTH), rand.nextInt(MAP_HEIGHT));
        } while (nextLocation.isWalkable(randomPosition));
        door = new Door(randomPosition, nextLocation);
    }

    public Door getDoor() {
        return door;
    }

    public void connectRoom(Room connectingRoom) {
        nextLocation = connectingRoom;
        generateDoor();
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

    public List<Character> getEnemies() {
        return enemies;
    }

    public void removeEnemy(Character enemy) {
        enemies.remove(enemy);
    }

    public void addEnemy(Character enemy) {
        enemies.add(enemy);
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public void addProjectile(Projectile projectile) {
        this.projectiles.add(projectile);
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    private void updateEnemies(Character player) {
        for (Character e : enemies) {
            e.doAction(this, player);
        }
    }
    private void updateProjectiles(Character player) {
        List<Projectile> toRemove = new ArrayList<>();
        for (Projectile projectile : projectiles) {
            projectile.move();
            if (!isWalkable(projectile.getPosition())) {
                toRemove.add(projectile);
                continue;
            }
            if (projectile.getOwner().equals(ProjectileOwner.ENEMY) && positionsEqual(projectile.getPosition(), player.getPosition())) {
                player.loseHealth(projectile.getDamage());
                toRemove.add(projectile);
            }
            else if (projectile.getOwner().equals(ProjectileOwner.PLAYER)) {
                for (Character enemy : enemies) {
                    if (positionsEqual(projectile.getPosition(), enemy.getPosition())) {
                        enemy.loseHealth(projectile.getDamage());
                        toRemove.add(projectile);
                    }
                }
            }
        }
        projectiles.removeAll(toRemove);
    }
    public void removeDeadEnemies() {
        enemies.removeIf(character -> !character.isAlive());
    }

    public void checkDoorState() {
        if (enemies.isEmpty()) {
            door.open();
        }
    }
    public void update(Character player) {
        updateEnemies(player);
        updateProjectiles(player);
        updateItems(player);
        removeDeadEnemies();
        if (door != null) {
            checkDoorState();
        }
    }

    public void updateItems(Character player) {
        List<Weapon> pickedUpWeapons = new ArrayList<>();
        for (Weapon weapon : weapons) {
            if (positionsEqual(weapon.getPosition(), player.getPosition())) {
                player.setProjectileType(weapon.getProjectileType());

                pickedUpWeapons.add(weapon);
            }
        }
        weapons.removeAll(pickedUpWeapons);
    }

    public Room getNextRoom() {
        return nextLocation;
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    boolean positionsEqual(Position one, Position two) {
        return one.getX() == two.getX() && one.getY() == two.getY();
    }

}
