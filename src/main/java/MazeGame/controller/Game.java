package MazeGame.controller;

import MazeGame.model.Maze;
import MazeGame.view.GameView;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        Maze maze = new Maze();
        GameView gv = new GameView(maze);
        JFrame frame = new JFrame("EXAMPLE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gv);
        frame.setSize(250, 250);
        frame.setVisible(true);
        gv.requestFocusInWindow();
    }
}
