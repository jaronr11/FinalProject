package MazeGame.controller;

import MazeGame.model.Door;
import MazeGame.model.GameEntities.*;
import MazeGame.model.GameEntities.Character;
import MazeGame.model.Maze;
import MazeGame.model.MovementStrategies.FollowMoveStrategy;
import MazeGame.model.MovementStrategies.NoMoveStrategy;
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
    private final CharacterFactory characterFactory = new CharacterFactory();
    private static final int TILE_ROWS = 2;
    private static final int TILE_COLS = 1;
    private static final double PLAYER_DEFAULT_HEALTH = 10;
    private static final double ENEMY_DEFAULT_HEALTH = 4;
    private static final Position ROOM_ONE_DOOR_POS = new Position(0, 0);
    private static final Position ROOM_ONE_WEAPON_POS = new Position(9, 5);
    private static final Position ROOM_TWO_DOOR_POS = new Position(5, 5);


    public void startGame() {
        createModel();
        createViewAndController();
        showWindow();
        gameController.startGame();
    }

    void createModel() {
        Position pos = new Position(0,0);
        Player p1 = characterFactory.createPlayer(pos, PLAYER_DEFAULT_HEALTH);

        Room roomOne = buildRoomOne();
        roomOne.generateRandomTiles(TILE_ROWS, TILE_COLS);
        Room roomTwo = buildRoomTwo();
        roomTwo.generateRandomTiles(TILE_ROWS, TILE_COLS);
        Room roomThree = buildRoomThree();
        roomThree.generateRandomTiles(TILE_ROWS, TILE_COLS);

        roomOne.connectRoom(roomTwo);
        roomTwo.connectRoom(roomThree);


        maze = new Maze(p1,roomOne);
        maze.setFinalRoom(roomThree);
    }

    Room buildRoomOne() {
        Character enemy = new Enemy(new NoMoveStrategy(), new Position(5,5), ENEMY_DEFAULT_HEALTH);
        Door door = new Door(ROOM_ONE_DOOR_POS);
        Weapon slowWeapon = new Weapon(ProjectileFactory.ProjectileType.SLOW_PROJECTILE, ROOM_ONE_WEAPON_POS);

        return new Room.Builder().addDoor(door).addEnemy(enemy).addWeapon(slowWeapon).build();
    }

    private Position[] buildRoomTwoObstacles() {
        return new Position[] {
                new Position(2,1), new Position(3,1), new Position(4,1),
                new Position(10,1), new Position(11,1), new Position(12,1),

                new Position(2,3), new Position(3,3), new Position(4,3),
                new Position(10,3), new Position(11,3), new Position(12,3),

                new Position(2,5), new Position(3,5), new Position(4,5),
                new Position(10,5), new Position(11,5), new Position(12,5),

                new Position(2,7), new Position(3,7), new Position(4,7),
                new Position(10,7), new Position(11,7), new Position(12,7)
        };
    }
    Room buildRoomTwo() {
        Character enemy = new Enemy(new RandomMoveStrategy(), new Position(7,5), ENEMY_DEFAULT_HEALTH);

        Position[] obstacles = buildRoomTwoObstacles();

        Door door = new Door(ROOM_TWO_DOOR_POS);
        return new Room.Builder().addObstacles(obstacles).addEnemy(enemy).addDoor(door).build();
    }

    Room buildRoomThree() {
        Position[] obstacles = buildRoomThreeObstacles();
        Character enemy = characterFactory.createEnemy(new FollowMoveStrategy(), new Position(8,5), ENEMY_DEFAULT_HEALTH);
        List<Character> enemies = List.of(enemy);
        Weapon slowWeapon = new Weapon(ProjectileFactory.ProjectileType.SLOW_PROJECTILE, new Position(9,5));

        return new Room.Builder().addEnemies(enemies).addObstacles(obstacles).addWeapon(slowWeapon).build();

    }

    private Position[] buildRoomThreeObstacles() {
        return new Position[] {
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
