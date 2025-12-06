import MazeGame.model.GameEntities.Player;
import MazeGame.model.GameEntities.ProjectileFactory;
import MazeGame.model.GameEntities.Weapon;
import MazeGame.model.Position;
import MazeGame.model.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    @Test
    public void testWeaponPickupChangesPlayerProjectileType() {
        Player player = new Player(new Position(2, 2), 10.0);

        Weapon weapon = new Weapon(ProjectileFactory.ProjectileType.BURST_PROJECTILE, new Position(2, 2)
        );

        Room room = new Room.Builder()
                .addWeapon(weapon)
                .build();

        ProjectileFactory.ProjectileType projectileBefore = player.getProjectileType();

        room.update(player);

        ProjectileFactory.ProjectileType projectileAfter = player.getProjectileType();

        assertNotEquals(projectileBefore, projectileAfter);
        assertEquals(ProjectileFactory.ProjectileType.BURST_PROJECTILE, projectileAfter);
        assertTrue(room.getWeapons().isEmpty());
    }
}
