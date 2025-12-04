package MazeGame.model.GameEntities;

import MazeGame.model.Position;

import java.util.ArrayList;
import java.util.List;

public class ProjectileFactory {
    public enum ProjectileType {
        NORMAL_PROJECTILE,
        BURST_PROJECTILE,
        SLOW_PROJECTILE,
        GOD_MODE
    }


    public List<Projectile> createProjectiles(ProjectileType weaponType, Position startPos, Position targetPos, ProjectileOwner owner) {
        List<Projectile> projectiles = new ArrayList<>();
        double HIGH_DAMAGE_MULTIPLIER = 2.25;
        int BIG_SIZE_MULTIPLIER = 2;
        int burstOffset = 5;
        double SLOW_PROJ_SPEED = .75;

        switch (weaponType) {
            case SLOW_PROJECTILE:
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
            case GOD_MODE:
                Projectile projectile =  new Projectile(startPos, targetPos, owner);
                projectile.setSpeed(3);
                projectile.setDamage(100);
                projectile.setSize(4);
                projectiles.add(projectile);
                break;

        }
        return projectiles;
    }


}
