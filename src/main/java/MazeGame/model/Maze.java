package MazeGame.model;
import MazeGame.model.GameEntities.*;
import MazeGame.model.GameEntities.Character;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private final Room currentRoom;
    private final Player player;

    public Maze() {
        this.currentRoom = new Room();
        this.player = new Player();
    }
    public Maze(Player player, Room room) {
        this.currentRoom = room;
        this.player = player;
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void movePlayer(Direction direction) {
        Position newPos = getNewPosition(player, direction);
        changeCharacterPosition(player, newPos);
        player.setLastDirection(direction);
    }
    public void moveCharacter(Character character, Direction direction) {
        Position newPos = getNewPosition(character, direction);
        changeCharacterPosition(character, newPos);
    }
    public Player getPlayer() {
        return player;
    }
    Position getNewPosition(Character character, Direction direction) {
        Position currentPos = character.getPosition();
        int x =  currentPos.getX();
        int y =  currentPos.getY();
        return switch (direction) {
            case UP -> new Position(x, y - 1);
            case DOWN -> new Position(x, y + 1);
            case LEFT -> new Position(x - 1, y);
            case RIGHT -> new Position(x + 1, y);
        };
    }
    void changeCharacterPosition(Character character, Position position) {
        Room currentRoom = getCurrentRoom();
        if (currentRoom.isWalkable(position)) {
            character.setPosition(position);
        }
        else {
            System.out.println("Out of bounds");
        }
    }
    Position getNewProjectilePosition(Projectile projectile) {
        Position currentPos = projectile.getPosition();
        Direction direction = projectile.getDirection();
        int speed =  projectile.getSpeed();
        int x =  currentPos.getX();
        int y =  currentPos.getY();
        return switch (direction) {
            case UP -> new Position(x, y - speed);
            case DOWN -> new Position(x, y + speed);
            case LEFT -> new Position(x - speed, y);
            case RIGHT -> new Position(x + speed, y);
        };
    }
    public void updateEnemies() {
        List<Enemy> enemies = currentRoom.getEnemies();
        for (Enemy enemy : enemies) {
            enemy.doAction(this);
        }
    }

    boolean positionsEqual(Position one, Position two) {
        return one.getX() == two.getX() && one.getY() == two.getY();
    }
    public void updateProjectiles() {
        List<Projectile> projectiles = currentRoom.getProjectiles();
        List<Projectile> toRemove = new ArrayList<>();
        for (Projectile projectile : projectiles) {
            Position newPos = getNewProjectilePosition(projectile);
            if (!currentRoom.isWalkable(newPos)) {
                toRemove.add(projectile);
                continue;
            }
            projectile.setPosition(newPos);
            if (projectile.getOwner().equals(ProjectileOwner.PLAYER)) {
                for (Enemy enemy : currentRoom.getEnemies()) {
                    if (positionsEqual(enemy.getPosition(), projectile.getPosition())) {
                        enemy.loseHealth(projectile.getDamage());
                        if (!enemy.isAlive()) { currentRoom.removeEnemy(enemy); }
                        toRemove.add(projectile);
                        break;
                    }
                }
            }
            else if (projectile.getOwner().equals(ProjectileOwner.ENEMY)) {
                if (positionsEqual(player.getPosition(), projectile.getPosition())) {
                    player.loseHealth(projectile.getDamage());
                    if (!player.isAlive()) {
                        break;
                    }
                    toRemove.add(projectile);
                }
            }
        }
        projectiles.removeAll(toRemove);
    }
    public void spawnPlayerProjectile(Direction direction) {
        Position startPos = player.getPosition();
        Projectile projectile = new Projectile(startPos, direction, ProjectileOwner.PLAYER);
        currentRoom.addProjectile(projectile);
    }
    public void spawnEnemyProjectile(Enemy enemy, Direction direction) {
        Position startPos = enemy.getPosition();
        Projectile projectile = new Projectile(startPos, direction, ProjectileOwner.ENEMY);
        currentRoom.addProjectile(projectile);
    }

    public Direction getDirectionTowardPlayer(Character enemy) {
        Position enemyPos = enemy.getPosition();
        Position playerPos = player.getPosition();

        int dx = playerPos.getX() - enemyPos.getX();
        int dy = playerPos.getY() - enemyPos.getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) { return Direction.RIGHT; }
            else { return  Direction.LEFT; }
        }
        else {
            if (dy > 0) { return Direction.DOWN; }
            else { return  Direction.UP; }
        }
    }

    public boolean isPlayerAlive() {
        return player.isAlive();
    }


}
