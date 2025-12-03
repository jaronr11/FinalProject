package MazeGame.model.GameEntities;

import MazeGame.model.Direction;
import MazeGame.model.Position;
import MazeGame.model.Room;
import MazeGame.model.GameEntities.ProjectileFactory.ProjectileType;

import java.util.List;

public class Player extends Character {
    private List<Weapon> inventory;
    private Direction lastDirection;
    ProjectileFactory projectileFactory = new ProjectileFactory();

    public Player(Position startPos, double initialHealth) {
        this.position = startPos;
        this.health = initialHealth;
        this.lastDirection = Direction.DOWN;
        this.projectileType = ProjectileType.NORMAL_PROJECTILE;
    }


    public void shoot(Position targetPos, Room room) {
        List<Projectile> projectiles = projectileFactory.createProjectiles(projectileType, position, targetPos, ProjectileOwner.PLAYER);
        for (Projectile projectile : projectiles) {
            room.addProjectile(projectile);
        }
    }

    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }


    @Override
    public void doAction(Room room, Character player) {
    }
}
