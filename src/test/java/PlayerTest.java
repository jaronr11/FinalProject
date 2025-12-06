import MazeGame.model.GameEntities.Player;
import MazeGame.model.Position;
import MazeGame.model.Room;
import MazeGame.model.GameEntities.Projectile;
import MazeGame.model.GameEntities.ProjectileOwner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testConstructor() {
        Position start = new Position(2, 3);
        double health = 15.0;

        Player player = new Player(start, health);

        assertEquals(2, player.getPosition().getX());
        assertEquals(3, player.getPosition().getY());
        assertEquals(health, player.getHealth());
    }

    @Test
    public void testPlayerShootAddsProjectileToRoom() {
        Player player = new Player(new Position(1, 1), 10.0);
        Room room = new Room.Builder().build();

        assertTrue(room.getProjectiles().isEmpty());

        player.shoot(new Position(4, 4), room);

        assertFalse(room.getProjectiles().isEmpty());
        Projectile proj = room.getProjectiles().get(0);
        assertEquals(ProjectileOwner.PLAYER, proj.getOwner());
    }
}
