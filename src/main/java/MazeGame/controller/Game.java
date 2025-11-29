package MazeGame.controller;

import MazeGame.model.GameEntities.CharacterFactory;
import MazeGame.model.GameEntities.Enemy;
import MazeGame.model.Maze;
import MazeGame.model.Position;
import MazeGame.model.MovementStrategies.RandomMoveStrategy;
import MazeGame.model.GameEntities.Character;
import MazeGame.model.Room;
import MazeGame.view.GameView;

import javax.swing.*;

public class Game {
    private static Timer timer;
    public static void main(String[] args) {
        CharacterFactory cf =  new CharacterFactory();
        Position pos = new Position(0,0);
        Character p1 = cf.createPlayer(pos, 10.0);
        Room currentRoom = new Room();
        Maze maze = new Maze(p1,currentRoom);
        Character e1 = new Enemy(new RandomMoveStrategy(), new Position(5,5), 10.0);
        Room room = maze.getCurrentRoom();
        room.addEnemy(e1);

        GameView gv = new GameView(maze);
        JFrame frame = new JFrame("EXAMPLE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gv);
        frame.setSize(250, 250);
        frame.setVisible(true);
        gv.requestFocusInWindow();

        //code from https://codingtechroom.com/tutorial/java-implementing-game-timers-java-2d-game-development
        int tickMillis = 200;
        timer = new Timer(tickMillis, e -> {
            maze.updateGame();
            if (!maze.isPlayerAlive()) {
                timer.stop();
                JOptionPane.showMessageDialog(frame, "Game Over!");
                return;
            }
            gv.repaint();
        });
        timer.start();
    }
}
