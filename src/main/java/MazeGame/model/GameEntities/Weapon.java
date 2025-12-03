package MazeGame.model.GameEntities;

import MazeGame.model.Position;

public class Weapon {
    Position position;
    ProjectileFactory.ProjectileType projectileType;
    ProjectileFactory projectileFactory = new ProjectileFactory();

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
