package MazeGame.view;

import MazeGame.model.*;
import MazeGame.model.GameEntities.Projectile;
import MazeGame.model.GameEntities.Character;
import MazeGame.model.GameEntities.Weapon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameView extends JPanel {
    Maze maze;
    int TILE_SIZE = 32;
    public GameView(Maze maze) {
        this.maze = maze;
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintMap(g);
        paintCharacters(g);
        paintProjectiles(g);
        paintItems(g);
        g.drawString(String.valueOf(maze.getPlayer().getHealth()), 400, 20);

    }

    public void paintMap(Graphics g) {
        Tile[][] map = maze.getCurrentRoom().getTiles();
        Door door =  maze.getCurrentRoom().getDoor();
        if (door == null) {
            door = new Door(new Position(0,0), null);
        }
        Position doorPos = door.getPosition();
        int doorX = doorPos.getX();
        int doorY = doorPos.getY();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                Tile tile = map[row][col];
                if (tile.isWalkable()) {
                    g.setColor(Color.WHITE);
                    g.fillRect(col*TILE_SIZE, row*TILE_SIZE,  TILE_SIZE, TILE_SIZE);
                }
                else {
                    g.setColor(Color.BLACK);
                    g.fillRect(col*TILE_SIZE, row*TILE_SIZE,  TILE_SIZE, TILE_SIZE);
                }
            }
        }
        if (door.isOpen()) {
            g.setColor(Color.GREEN);
            g.fillArc(doorX*TILE_SIZE, doorY*TILE_SIZE, TILE_SIZE, TILE_SIZE, 90, 180);
        }
        else {
            g.setColor(Color.RED);
            g.fillArc(doorX*TILE_SIZE, doorY*TILE_SIZE, TILE_SIZE, TILE_SIZE, 90, 180);
        }
    }
    public void paintProjectiles(Graphics g) {
        for (Projectile projectile : maze.getCurrentRoom().getProjectiles()) {
            Position pos = projectile.getPosition();
            int px =  pos.getX() *TILE_SIZE;
            int py =   pos.getY() *TILE_SIZE;
            g.fillOval(px, py, TILE_SIZE/2, TILE_SIZE/2);
        }
    }
    public void paintCharacters(Graphics g) {
        List<Character> enemies = maze.getCurrentRoom().getEnemies();
        Position playerPos = maze.getPlayer().getPosition();
        g.setColor(Color.BLUE);
        g.fillRect(playerPos.getX() *TILE_SIZE,playerPos.getY()*TILE_SIZE, TILE_SIZE, TILE_SIZE);
        g.setColor(Color.RED);
        for (Character enemy : enemies) {
            Position pos = enemy.getPosition();
            g.fillRect(pos.getX() * TILE_SIZE,
                    pos.getY()*TILE_SIZE,  TILE_SIZE, TILE_SIZE);
        }

    }
    public void paintItems(Graphics g) {
        for (Weapon weapon : maze.getCurrentRoom().getWeapons()) {
            Position pos = weapon.getPosition();
            int px =  pos.getX() *TILE_SIZE;
            int py =   pos.getY() *TILE_SIZE;
            g.setColor(Color.BLACK);
            g.fillOval(px, py, TILE_SIZE/2, TILE_SIZE/2);
        }
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

}
