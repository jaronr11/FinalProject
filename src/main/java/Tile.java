import java.awt.*;

//code from https://codingtechroom.com/tutorial/java-how-to-use-tile-maps-for-building-engaging-game-levels-in-java
public class Tile {
    private Image texture;
    private boolean walkable;

    public Tile(Image texture, boolean walkable) {
        this.texture = texture;
        this.walkable = walkable;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public Image getTexture() {
        return texture;
    }
}