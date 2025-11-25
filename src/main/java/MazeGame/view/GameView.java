package MazeGame.view;

import MazeGame.model.Direction;
import MazeGame.model.Maze;
import MazeGame.model.Position;
import MazeGame.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameView extends JPanel implements KeyListener {
    Maze maze;
    int TILE_SIZE = 32;
    public GameView(Maze maze) {
        this.maze = maze;
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Position playerPos = maze.getPlayer().getPosition();
        Tile[][] map = maze.getCurrentRoom().getTiles();
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
        g.setColor(Color.RED);
        g.fillRect(playerPos.getX() *TILE_SIZE,playerPos.getY()*TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            maze.movePlayer(Direction.LEFT);
            repaint();
        } else if (key == KeyEvent.VK_RIGHT) {
            maze.movePlayer(Direction.RIGHT);
            repaint();
        }
        else if (key == KeyEvent.VK_UP) {
            maze.movePlayer(Direction.UP);
            repaint();
        }
        else if (key == KeyEvent.VK_DOWN) {
            maze.movePlayer(Direction.DOWN);
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
