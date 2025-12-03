package MazeGame.model;

public class Door {
    private Position position;
    private Room nextLocation;
    private Position nextLocationPos;
    private boolean isOpen;

    public Door(Position position, Room nextLocation) {
        this.position = position;
        this.nextLocation = nextLocation;
        this.isOpen = false;
    }

    public void open() {
        this.isOpen = true;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
