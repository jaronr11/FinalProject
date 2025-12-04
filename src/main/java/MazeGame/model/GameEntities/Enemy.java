package MazeGame.model.GameEntities;

import MazeGame.model.*;
import MazeGame.model.MovementStrategies.MovementStrategy;
import MazeGame.model.MovementStrategies.RandomMoveStrategy;

public class Enemy extends Character {
    MovementStrategy strategy;
    private int moveCooldown = 0;
    private int moveDelay = 1;

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
        if (moveCooldown > 0) {
            moveCooldown--;
            shootProjectile(room, player);
            return;
        }
        moveCooldown = moveDelay;
        Direction moveDir = strategy.move(position, player);

        move(moveDir, room);
        shootProjectile(room, player);

    }

    void shootProjectile(Room room, Character player) {
        if (Math.random() < 0.35) { //rng
            Position bulletStartPos = new Position(this.position.getX(), this.position.getY());
            Position targetPos = player.getPosition();
            room.addProjectile(new Projectile(bulletStartPos, targetPos, ProjectileOwner.ENEMY));
        }
    }
}
