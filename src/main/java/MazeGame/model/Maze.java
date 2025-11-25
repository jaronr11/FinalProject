package MazeGame.model;

import MazeGame.model.GameEntities.Player;

public class Maze {
    private final Room currentRoom;
    private final Player player;

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
        Position newPos = getNewPosition(direction);
        changeCharacterPosition(newPos);
    }
    public Player getPlayer() {
        return player;
    }
    Position getNewPosition(Direction direction) {
        Position currentPos = player.getPosition();
        int x =  currentPos.getX();
        int y =  currentPos.getY();
        return switch (direction) {
            case UP -> new Position(x, y - 1);
            case DOWN -> new Position(x, y + 1);
            case LEFT -> new Position(x - 1, y);
            case RIGHT -> new Position(x + 1, y);
        };
    }
    void changeCharacterPosition(Position position) {
        Room currentRoom = getCurrentRoom();
        if (currentRoom.isWalkable(position)) {
            player.setPosition(position);
        }
        else {
            System.out.println("Out of bounds");
        }
    }

//    public void render(Graphics g) {
//        for (int row = 0; row < map.length; row++) {
//            for (int col = 0; col < map[row].length; col++) {
//                Tile tile = map[row][col];
//                g.drawImage(tile.getTexture(), col * tileSize, row * tileSize, null);
//            }
//        }
//    }


}
