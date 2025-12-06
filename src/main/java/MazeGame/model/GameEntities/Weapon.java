package MazeGame.model.GameEntities;

import MazeGame.model.Position;

public class Weapon {
    private final Position position;
    private final ProjectileFactory.ProjectileType projectileType;

    public Weapon(ProjectileFactory.ProjectileType projectileType, Position position) {
        this.position = position;
        this.projectileType = projectileType;
    }

    public Position getPosition() {
        return position;
    }

    public ProjectileFactory.ProjectileType getProjectileType() {
        return projectileType;
    }
}
