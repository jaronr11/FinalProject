import java.util.HashMap;
import java.util.Map;

public class Maze {
    protected Map<Room, Position> state;
    private int mapHeight;
    private int mapWidth;
    Tile[][] map = new Tile[mapHeight][mapWidth];

    public Maze() {
        state = new HashMap<>();
    }

    public void add(Room room, Position position) {
        state.put(room, position);
    }

    public Position getRoomPos(Room room) {
        return state.get(room);
    }

    public void render(Graphics g) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                Tile tile = map[row][col];
                g.drawImage(tile.getTexture(), col * tileSize, row * tileSize, null);
            }
        }
    }


}
