package MazeGame.controller;

import MazeGame.model.Direction;
import MazeGame.model.Maze;
import MazeGame.model.Position;
import MazeGame.view.GameView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.*;

public class GameController implements KeyListener, MouseListener {
    Maze maze;
    GameView gameView;
    private Timer timer;
    JFrame frame;

    public GameController(Maze maze, GameView gameView, JFrame frame) {
        this.maze = maze;
        this.gameView = gameView;
        this.frame = frame;
    }

    public void startGame() {
        //code from https://codingtechroom.com/tutorial/java-implementing-game-timers-java-2d-game-development
        int tickMillis = 200;
        timer = new Timer(tickMillis, e -> {
            maze.updateGame();
            gameView.updateFrames();
            if (!maze.isPlayerAlive()) {
                timer.stop();
                JOptionPane.showMessageDialog(frame, "Game Over!");
                return;
            }
            gameView.repaint();
        });
        timer.start();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            maze.movePlayer(Direction.LEFT);

        } else if (key == KeyEvent.VK_D) {
            maze.movePlayer(Direction.RIGHT);
        }
        else if (key == KeyEvent.VK_W) {
            maze.movePlayer(Direction.UP);
        }
        else if (key == KeyEvent.VK_S) {
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
        Position targetPos = new Position(mouseX/gameView.getTileSize(), mouseY/gameView.getTileSize());
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
