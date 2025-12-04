package MazeGame.controller;

import MazeGame.model.GameEntities.*;


import javax.swing.*;


public class Game {
    public static void main(String[] args) {
        GameFacade gameFacade = new GameFacade();
        gameFacade.startGame();
    }
}
