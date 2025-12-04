package MazeGame.model.GameEntities;

import MazeGame.model.Room;
import MazeGame.model.Position;

import java.util.ArrayList;
import java.util.List;

public class ProjectileFactory {
    public enum ProjectileType {
        NORMAL_PROJECTILE,
        BURST_PROJECTILE,
        SLOW_PROJECTILE,
    }


    public List<Projectile> createProjectiles(ProjectileType weaponType, Position startPos, Position targetPos, ProjectileOwner owner) {
        List<Projectile> projectiles = new ArrayList<>();
        double HIGH_DAMAGE_MULTIPLIER = 2.5;
        int BIG_SIZE_MULTIPLIER = 2;
        int burstOffset = 5;
        int SLOW_PROJ_SPEED = 1;

        switch (weaponType) {
            case  SLOW_PROJECTILE:
                Projectile slowProjectile = new Projectile(startPos, targetPos, owner);
                slowProjectile.setSpeed(SLOW_PROJ_SPEED);
                slowProjectile.setDamage(HIGH_DAMAGE_MULTIPLIER);
                slowProjectile.setSize(BIG_SIZE_MULTIPLIER);
                projectiles.add(slowProjectile);
                break;

            case BURST_PROJECTILE:
                Projectile  burstProjectileCenter = new Projectile(startPos, targetPos, owner);
                Position rightOffset = new Position(targetPos.getX() + burstOffset, targetPos.getY() + burstOffset);
                Projectile burstProjectileRight = new Projectile(startPos, rightOffset, owner);

                projectiles.add(burstProjectileRight);
                projectiles.add(burstProjectileCenter);
                break;
            case NORMAL_PROJECTILE:
                projectiles.add(new Projectile(startPos, targetPos, owner));
                break;
        }
        return projectiles;
    }


}
