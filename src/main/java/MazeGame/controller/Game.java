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
        GameFacade gameFacade = new GameFacade();
        gameFacade.startGame();
    }
}
