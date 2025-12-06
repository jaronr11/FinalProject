package MazeGame.model.GameEntities;

import MazeGame.model.Direction;
import MazeGame.model.Position;
import MazeGame.model.Room;
import MazeGame.model.GameEntities.ProjectileFactory.ProjectileType;

import java.util.List;

public class Player extends Character {
    private final ProjectileFactory projectileFactory = new ProjectileFactory();

    public Player(Position startPos, double initialHealth) {
        setPosition(startPos);
        setHealth(initialHealth);
        setProjectileType(ProjectileType.NORMAL_PROJECTILE);
    }


    public void shoot(Position targetPos, Room room) {
        List<Projectile> projectiles = projectileFactory.createProjectiles(getProjectileType(), getPosition(), targetPos, ProjectileOwner.PLAYER);
        for (Projectile projectile : projectiles) {
            room.addProjectile(projectile);
        }
    }

    @Override
    public void doAction(Room room, Character player) {
    }
}
