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
    private List<Character> enemies = new ArrayList<>();
    private final List<Projectile> projectiles = new ArrayList<>();
    private List<Weapon> weapons = new ArrayList<>();
    private final List<SmokeEffect> smokeEffects = new ArrayList<>();
    private Position[] obstacles;
    private Door door;
    private Room nextLocation;
    private int[][] tileSpriteRow;
    private int[][] tileSpriteCol;


    public static class Builder {
        private final List<Character> enemies =  new ArrayList<>();
        private final List<Weapon> weapons =  new ArrayList<>();
        private Door door;
        private Room nextLocation;
        private Position[] obstacles;

        public Builder addEnemies(List<Character> enemies) {
            this.enemies.addAll(enemies);
            return this;
        }
        public Builder addEnemy(Character enemy) {
            this.enemies.add(enemy);
            return this;
        }

        public Builder addObstacles(Position[] obstacles) {
            this.obstacles = obstacles;
            return this;
        }

        public Builder addWeapons(List<Weapon> weapons) {
            this.weapons.addAll(weapons);
            return this;
        }

        public Builder addWeapon(Weapon weapon) {
            this.weapons.add(weapon);
            return this;
        }

        public Builder addDoor(Door door) {
            this.door = door;
            return this;
        }

        public Room build() {
            Room room;
            if (obstacles == null || obstacles.length == 0) {
                room = new Room();
            }
            else {
                room = new Room(obstacles);
            }
            room.enemies = enemies;
            room.weapons = weapons;
            room.door = door;
            room.nextLocation = nextLocation;
            return room;
        }

    }

    private Room() {
        for (int y=0; y<MAP_HEIGHT; y++) {
            for (int x=0; x<MAP_WIDTH; x++) {
                tiles[y][x] = new Tile(null, true);
            }
        }
    }
    private Room(Position[] obstacles) {
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

    public Door getDoor() {
        return door;
    }

    public void connectRoom(Room connectingRoom) {
        nextLocation = connectingRoom;
    }
    public boolean isWalkable(Position position) {
        int x = position.x;
        int y = position.y;
        if (x < 0 || x >= MAP_WIDTH || y < 0 || y >= MAP_HEIGHT) {
            return false;
        }
        return tiles[y][x].isWalkable();
    }

    public int getMapHeight() {
        return MAP_HEIGHT;
    }
    public int getMapWidth() {
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
        for (Character enemy : enemies) {
            enemy.doAction(this, player);
        }
    }
    private void updateProjectiles(Character player) {
        List<Projectile> toRemove = new ArrayList<>();
        for (Projectile projectile : projectiles) {
            projectile.move();
            if (projectile.getX() < 0 || projectile.getX() >= getMapWidth() || projectile.getY() < 0 || projectile.getY() >= getMapHeight()) {
                toRemove.add(projectile);
                continue;
            }

            if (!isWalkable(projectile.getPosition())) {
                toRemove.add(projectile);
                continue;
            }

            if (projectile.getOwner().equals(ProjectileOwner.ENEMY) && projectileHitCharacter(projectile, player)) {
                player.loseHealth(projectile.getDamage());
                toRemove.add(projectile);
            }
            else if (projectile.getOwner().equals(ProjectileOwner.PLAYER)) {
                for (Character enemy : enemies) {
                    if (projectileHitCharacter(projectile, enemy)) {
                        enemy.loseHealth(projectile.getDamage());
                        toRemove.add(projectile);
                    }
                }
            }
        }
        projectiles.removeAll(toRemove);
    }
    public void removeDeadEnemies() {
        List<Character> deadEnemies = new ArrayList<>();
        for (Character enemy : enemies) {
            if (!enemy.isAlive()) {
                deadEnemies.add(enemy);
                smokeEffects.add(new SmokeEffect(enemy.getPosition()));
            }
        }
        enemies.removeAll(deadEnemies);
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

    boolean positionsEqual(Position one, Position two) {
        return one.getX() == two.getX() && one.getY() == two.getY();
    }

    boolean projectileHitCharacter(Projectile projectile, Character character) {
        Position charPos = character.getPosition();
        Position projPos = projectile.getPosition();

        double dx  = projPos.getX() - charPos.getX();
        double dy  = projPos.getY() - charPos.getY();
        double halfSize = projectile.getSize()/2.0;

        return Math.abs(dx) <= halfSize && Math.abs(dy) <= halfSize;
    }

    public List<SmokeEffect> getSmokeEffects() {
        return smokeEffects;
    }

    public void addSmokeEffect(SmokeEffect smokeEffect) {
        smokeEffects.add(smokeEffect);
    }

    public void removeSmokeEffects() {
        smokeEffects.removeIf(SmokeEffect::isFinished);
    }


    public int getTileRow(int row, int col) {
        return tileSpriteRow[row][col];
    }

    public int getTileCol(int row, int col) {
        return tileSpriteCol[row][col];
    }

    public void generateRandomTiles(int maxRows, int maxCols) {
        tileSpriteRow = new int[MAP_HEIGHT][MAP_WIDTH];
        tileSpriteCol = new int[MAP_HEIGHT][MAP_WIDTH];

        Random rand = new Random();
        for (int row = 0; row < MAP_HEIGHT; row++) {
            for (int col = 0; col < MAP_WIDTH; col++) {
                tileSpriteRow[row][col] = rand.nextInt(maxRows);
                tileSpriteCol[row][col] = rand.nextInt(maxCols);
            }
        }
    }
}
