package MazeGame.model;
import MazeGame.model.GameEntities.*;
import MazeGame.model.GameEntities.Character;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private final Room currentRoom;
    private final Character player;

    public Maze() {
        this.currentRoom = new Room();
        this.player = new Player();
    }
    public Maze(Player player, Room room) {
        this.currentRoom = room;
        this.player = player;
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void movePlayer(Direction direction) {
        player.move(direction, currentRoom);
    }
    public void moveCharacter(Character character, Direction direction) {
        character.move(direction, currentRoom);
    }
    public Character getPlayer() {
        return player;
    }

    public void updateGame() {
        currentRoom.update(player);

        if (!player.isAlive()) {
            System.out.println("Game over!");
        }
    }

    public void spawnPlayerProjectile(Direction direction) {
        Position startPos = player.getPosition();
        Projectile projectile = new Projectile(startPos, direction, ProjectileOwner.PLAYER);
        currentRoom.addProjectile(projectile);
    }


    public boolean isPlayerAlive() {
        return player.isAlive();
    }


}
