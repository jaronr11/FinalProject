import MazeGame.model.Direction;
import MazeGame.model.GameEntities.Player;
import MazeGame.model.Maze;
import MazeGame.model.Position;
import MazeGame.model.Room;
import MazeGame.model.Door;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    @Test
    public void testMovePlayerChangesPosition() {
        Player player = new Player(new Position(0, 0), 10.0);
        Room room = new Room.Builder().build();
        Maze maze = new Maze(player, room);

        maze.movePlayer(Direction.DOWN);

        assertEquals(0, player.getPosition().getX());
        assertEquals(1, player.getPosition().getY());
    }

    @Test
    public void testSpawnPlayerProjectileAddsProjectileToRoom() {
        Player player = new Player(new Position(1, 1), 10.0);
        Room room = new Room.Builder().build();
        Maze maze = new Maze(player, room);

        assertTrue(room.getProjectiles().isEmpty());

        maze.spawnPlayerProjectile(new Position(3, 3));

        assertFalse(room.getProjectiles().isEmpty());
    }

    @Test
    public void testDoorTransitionToNextRoom() {
        Player player = new Player(new Position(0, 0), 10.0);

        Door door = new Door(new Position(0, 0));
        Room firstRoom = new Room.Builder()
                .addDoor(door)
                .build();
        Room secondRoom = new Room.Builder().build();
        firstRoom.connectRoom(secondRoom);

        Maze maze = new Maze(player, firstRoom);

        door.open();

        maze.updateGame();

        assertSame(secondRoom, maze.getCurrentRoom());
    }
}
