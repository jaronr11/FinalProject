package MazeGame.model.GameEntities;

import MazeGame.model.*;

public class Enemy extends Character {
    MovementStrategy strategy;

    private double DEFAULT_ENEMY_HEALTH = 5.0;

    public Enemy() {
        this.strategy = new RandomMoveStrategy();
        this.position = new Position(0,0);
        this.health = DEFAULT_ENEMY_HEALTH;
    }
    public Enemy(MovementStrategy strategy, Position position, double health) {
        this.strategy = strategy;
        this.position = position;
        this.health = health;
    }
    @Override
    public void doAction(Room room, Character player) {
        Direction moveDir = strategy.move();
        move(moveDir, room);

        if (Math.random() < 0.2) {
            Direction playerTargetDir = getDirectionTowardPlayer(player);
            Position bulletStartPos = new Position(this.position.getX(), this.position.getY());
            room.addProjectile(new Projectile(bulletStartPos, playerTargetDir, ProjectileOwner.ENEMY));
        }
    }

    public Direction getDirectionTowardPlayer(Character player) {
        Position playerPos = player.getPosition();
        Position enemyPos = this.getPosition();

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


}
