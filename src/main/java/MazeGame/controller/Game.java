package MazeGame.controller;

import MazeGame.model.GameEntities.*;
import MazeGame.model.GameEntities.Character;
import MazeGame.model.Maze;
import MazeGame.model.MovementStrategies.FollowMove;
import MazeGame.model.Position;
import MazeGame.model.MovementStrategies.RandomMoveStrategy;
import MazeGame.model.Room;
import MazeGame.view.GameView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        CharacterFactory cf =  new CharacterFactory();
        Position pos = new Position(0,0);
        Player p1 = cf.createPlayer(pos, 1);
        Room roomOne = new Room();
        Position[] positions = {new  Position(0,5),new Position(3,0),new Position(7,4)};
        Room roomTwo = new Room(positions);
        roomOne.connectRoom(roomTwo);
        Maze maze = new Maze(p1,roomOne);
        Character e1 = new Enemy(new RandomMoveStrategy(), new Position(5,5), 1.0);
        Room room = maze.getCurrentRoom();
        room.addEnemy(e1);
        Character e2 = new Enemy(new FollowMove(), new Position(5,5), 10.0 );
        roomTwo.addEnemy(e2);

        Weapon weapon = new Weapon(ProjectileFactory.ProjectileType.BURST_PROJECTILE, new Position(3,5));
        room.addWeapon(weapon);

        JFrame frame = new JFrame("EXAMPLE");

        GameView gv = new GameView(maze);
        GameController gc = new GameController(maze, gv, frame);
        gv.addKeyListener(gc);
        gv.addMouseListener(gc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gv);
        frame.setSize(500, 750);
        frame.setVisible(true);
        gv.requestFocusInWindow();

       gc.startGame();
    }
}
