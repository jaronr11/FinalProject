package MazeGame.view;

import MazeGame.model.Direction;
import MazeGame.model.GameEntities.Enemy;
import MazeGame.model.GameEntities.Projectile;
import MazeGame.model.Maze;
import MazeGame.model.Position;
import MazeGame.model.Tile;
import MazeGame.model.GameEntities.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class GameView extends JPanel implements KeyListener, MouseListener {
    Maze maze;
    int TILE_SIZE = 32;
    public GameView(Maze maze) {
        this.maze = maze;
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Character> enemies = maze.getCurrentRoom().getEnemies();
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
        g.setColor(Color.BLUE);
        g.fillRect(playerPos.getX() *TILE_SIZE,playerPos.getY()*TILE_SIZE, TILE_SIZE, TILE_SIZE);
        g.setColor(Color.RED);
        for (Character enemy : enemies) {
            Position pos = enemy.getPosition();
            g.fillRect(pos.getX() * TILE_SIZE,
                    pos.getY()*TILE_SIZE,  TILE_SIZE, TILE_SIZE);
        }

        for (Projectile projectile : maze.getCurrentRoom().getProjectiles()) {
            Position pos = projectile.getPosition();
            int px =  pos.getX() *TILE_SIZE;
            int py =   pos.getY() *TILE_SIZE;
            g.fillOval(px, py, TILE_SIZE/2, TILE_SIZE/2);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            maze.movePlayer(Direction.LEFT);

        } else if (key == KeyEvent.VK_RIGHT) {
            maze.movePlayer(Direction.RIGHT);
        }
        else if (key == KeyEvent.VK_UP) {
            maze.movePlayer(Direction.UP);
        }
        else if (key == KeyEvent.VK_DOWN) {
            maze.movePlayer(Direction.DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        Position targetPos = new Position(mouseX/TILE_SIZE, mouseY/TILE_SIZE);
        maze.spawnPlayerProjectile(targetPos);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
