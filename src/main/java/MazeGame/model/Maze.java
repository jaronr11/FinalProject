package MazeGame.model;
import MazeGame.model.GameEntities.*;
import MazeGame.model.GameEntities.Character;

public class Maze {
    private Room currentRoom;
    private final Player player;
    private Room finalRoom;
    private boolean gameWon = false;
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
        checkWinCondition();
    }

    void doorUpdate() {
        Door door = currentRoom.getDoor();
        if (currentRoom.positionsEqual(door.getPosition(), player.getPosition()) && door.isOpen()) {
            currentRoom = currentRoom.getNextRoom();
        }
    }
    private void checkWinCondition() {
        if (currentRoom == finalRoom &&
                currentRoom.getEnemies().isEmpty()) {
            gameWon = true;
        }
    }
    public boolean isGameWon() {
        return gameWon;
    }
    public void spawnPlayerProjectile(Position targetPos) {
        player.shoot(targetPos,  currentRoom);
    }

    public void setFinalRoom(Room room) {
        this.finalRoom = room;
    }
    public boolean isPlayerAlive() {
        return player.isAlive();
    }


}
