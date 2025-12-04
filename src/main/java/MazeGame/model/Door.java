package MazeGame.model;

public class Door {
    private Position position;
    private boolean isOpen;

    public Door(Position position) {
        this.position = position;
        this.isOpen = false;
    }

    public Door(Position position, Room nextLocation) {
        this.position = position;
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
