package MazeGame.model;
import MazeGame.model.GameEntities.*;
import MazeGame.model.GameEntities.Character;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private Room currentRoom;
    private final Player player;

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
    public Character getPlayer() {
        return player;
    }

    public void updateGame() {
        currentRoom.update(player);
        if (currentRoom.getDoor() != null) {
            doorUpdate();
        }
    }

    void doorUpdate() {
        Door door = currentRoom.getDoor();
        if (currentRoom.positionsEqual(door.getPosition(), player.getPosition()) && door.isOpen()) {
            currentRoom = currentRoom.getNextRoom();
        }
    }

    public void spawnPlayerProjectile(Position targetPos) {
        player.shoot(targetPos,  currentRoom);
    }


    public boolean isPlayerAlive() {
        return player.isAlive();
    }


}
