package MazeGame.model.GameEntities;

import MazeGame.model.*;
import MazeGame.model.MovementStrategies.MovementStrategy;
import MazeGame.model.MovementStrategies.NoMoveStrategy;
import MazeGame.model.MovementStrategies.RandomMoveStrategy;

public class Enemy extends Character {
    private final MovementStrategy strategy;
    private int moveCooldown = 0;
    private final int moveDelay = 1;
    private final double DEFAULT_ENEMY_HEALTH = 4.0;
    private static final double PROJECTILE_FIRE_CHANCE = .35;
    public Enemy(MovementStrategy strategy, Position position, double health) {
        this.strategy = strategy;
        setPosition(position);
        setHealth(health);
    }
    @Override
    public void doAction(Room room, Character player) {
        if (moveCooldown > 0) {
            moveCooldown--;
            shootProjectile(room, player);
            return;
        }
        moveCooldown = moveDelay;
        Direction moveDir = strategy.move(getPosition(), player);

        move(moveDir, room);
        shootProjectile(room, player);

    }

    void shootProjectile(Room room, Character player) {
        if (Math.random() < PROJECTILE_FIRE_CHANCE) { //rng
            Position bulletStartPos = new Position(getPosition().getX(), getPosition().getY());
            Position targetPos = player.getPosition();
            room.addProjectile(new Projectile(bulletStartPos, targetPos, ProjectileOwner.ENEMY));
        }
    }
}
