package MazeGame.controller;

import MazeGame.model.Door;
import MazeGame.model.GameEntities.*;
import MazeGame.model.GameEntities.Character;
import MazeGame.model.Maze;
import MazeGame.model.MovementStrategies.FollowMove;
import MazeGame.model.MovementStrategies.RandomMoveStrategy;
import MazeGame.model.Position;
import MazeGame.model.Room;
import MazeGame.view.GameView;

import javax.swing.*;
import java.util.List;

public class GameFacade {

    private Maze maze;
    private GameView gameView;
    private GameController gameController;
    private JFrame frame;
    CharacterFactory characterFactory = new CharacterFactory();
    private static final int TILE_ROWS = 2;
    private static final int TILE_COLS = 1;

    public void startGame() {
        createModel();
        createViewAndController();
        showWindow();
        gameController.startGame();
    }

    void createModel() {
        Position pos = new Position(0,0);
        Player p1 = characterFactory.createPlayer(pos, 10);

        Room roomOne = buildRoomOne();
        roomOne.generateRandomTiles(TILE_ROWS, TILE_COLS);
        Room roomTwo = buildRoomTwo();
        roomTwo.generateRandomTiles(TILE_ROWS, TILE_COLS);
        Room roomThree = buildRoomThree();
        roomThree.generateRandomTiles(TILE_ROWS, TILE_COLS);

        roomOne.connectRoom(roomTwo);
        roomTwo.connectRoom(roomThree);


        maze = new Maze(p1,roomOne);
    }

    Room buildRoomOne() {
        Character e1 = new Enemy(new RandomMoveStrategy(), new Position(5,5), 4.0);
        Door door = new Door(new Position(0,0));
        return new Room.Builder().addDoor(door).addEnemy(e1).build();
    }

    Room buildRoomTwo() {
        Character e2 = new Enemy(new RandomMoveStrategy(), new Position(7,5), 3.0 );

        Position[] obstacles = {
                new Position(2,1), new Position(3,1), new Position(4,1),
                new Position(10,1), new Position(11,1), new Position(12,1),

                new Position(2,3), new Position(3,3), new Position(4,3),
                new Position(10,3), new Position(11,3), new Position(12,3),

                new Position(2,5), new Position(3,5), new Position(4,5),
                new Position(10,5), new Position(11,5), new Position(12,5),

                new Position(2,7), new Position(3,7), new Position(4,7),
                new Position(10,7), new Position(11,7), new Position(12,7)
        };
        Door door = new Door(new Position(5,5));
        return new Room.Builder().addObstacles(obstacles).addEnemy(e2).addDoor(door).build();
    }

    Room buildRoomThree() {
        Position[] obstacles = {
                new Position(2,1), new Position(3,1), new Position(4,1),
                new Position(5,1), new Position(6,1), new Position(7,1),
                new Position(8,1), new Position(9,1), new Position(10,1),
                new Position(11,1), new Position(12,1),

                new Position(2,7), new Position(3,7), new Position(4,7),
                new Position(5,7), new Position(6,7), new Position(7,7),
                new Position(8,7), new Position(9,7), new Position(10,7),
                new Position(11,7), new Position(12,7),

                new Position(2,2), new Position(2,3),
                new Position(2,5), new Position(2,6),

                new Position(12,2), new Position(12,3),new Position(12,4),
                new Position(12,5), new Position(12,6)
        };
        Door door = new Door(new Position(14,0));
        Character enemyOne = characterFactory.createEnemy(new FollowMove(), new Position(8,5), 4.0);
        List<Character> enemies = List.of(enemyOne);
        Weapon slowWeapon = new Weapon(ProjectileFactory.ProjectileType.SLOW_PROJECTILE, new Position(9,5));

        return new Room.Builder().addEnemies(enemies).addObstacles(obstacles).addDoor(door).addWeapon(slowWeapon).build();

    }

    void createViewAndController() {
        frame = new JFrame("MazeGame");
        gameView = new GameView(maze);
        gameController = new GameController(maze, gameView, frame);
        gameView.addKeyListener(gameController);
        gameView.addMouseListener(gameController);
    }

    void showWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameView);
        frame.setSize(500, 750);
        frame.setVisible(true);
        gameView.requestFocusInWindow();
    }

}
