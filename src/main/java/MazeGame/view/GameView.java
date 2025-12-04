package MazeGame.view;

import MazeGame.model.*;
import MazeGame.model.GameEntities.Projectile;
import MazeGame.model.GameEntities.Character;
import MazeGame.model.GameEntities.ProjectileOwner;
import MazeGame.model.GameEntities.Weapon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GameView extends JPanel {
    Maze maze;
    int TILE_SIZE = 32;

    private static final int FRAME_W = 32;
    private static final int FRAME_H = 32;

    private BufferedImage[] characterFrames;
    private BufferedImage[] projectileFrames;
    private BufferedImage[] enemyProjectileFrames;
    private BufferedImage[] enemyFrames;
    private int currentFrame = 0;
    private int tick = 0;
    private static final int FRAME_SPEED = 1;

    public GameView(Maze maze) {

        this.maze = maze;
        //private final BufferedImage tileSprite;
        BufferedImage characterSprite;
        BufferedImage projectileSprite;
        try {
            projectileSprite = loadSprite("images/projectile.png");
            characterSprite =  loadSprite("images/character.png");
            SpriteSheet characterSheet = new SpriteSheet(characterSprite);
            SpriteSheet projectileSheet = new SpriteSheet(projectileSprite);

            int characterRow = 2;
            int enemyRow = 1;
            characterFrames = new BufferedImage[4];
            enemyFrames = new BufferedImage[4];
            projectileFrames = new BufferedImage[4];
            enemyProjectileFrames = new BufferedImage[4];
            for (int i =0; i<4; i++) {
                characterFrames[i] = characterSheet.getFrame(i, characterRow, FRAME_W, FRAME_H);
                projectileFrames[i] = projectileSheet.getFrame(i, 1, FRAME_W, FRAME_H);
                enemyFrames[i] = characterSheet.getFrame(i, enemyRow, FRAME_W, FRAME_H);
                enemyProjectileFrames[i] = projectileSheet.getFrame(i,0,FRAME_W,FRAME_H);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
            projectileSprite = null;
            characterSprite = null;
        }
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintMap(g);
        paintCharacters(g);

        paintProjectiles(g);

        paintItems(g);
        g.drawString(String.valueOf(maze.getPlayer().getHealth()), 400, 20);

    }

    public void paintMap(Graphics g) {
        Tile[][] map = maze.getCurrentRoom().getTiles();
        Door door =  maze.getCurrentRoom().getDoor();
        if (door == null) {
            door = new Door(new Position(0,0), null);
        }
        Position doorPos = door.getPosition();
        int doorX = doorPos.getX();
        int doorY = doorPos.getY();
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
        if (door.isOpen()) {
            g.setColor(Color.GREEN);
            g.fillArc(doorX*TILE_SIZE, doorY*TILE_SIZE, TILE_SIZE, TILE_SIZE, 90, 180);
        }
        else {
            g.setColor(Color.RED);
            g.fillArc(doorX*TILE_SIZE, doorY*TILE_SIZE, TILE_SIZE, TILE_SIZE, 90, 180);
        }
    }
    public void paintProjectiles(Graphics g) {
        for (Projectile projectile : maze.getCurrentRoom().getProjectiles()) {
            Position  projectilePos = projectile.getPosition();
            double px =  projectilePos.getX() *TILE_SIZE;
            double py =   projectilePos.getY() *TILE_SIZE;
            int drawSize = (int) (TILE_SIZE * projectile.getSize());
            int offset = (drawSize-TILE_SIZE)/2;
            if (projectile.getOwner().equals(ProjectileOwner.PLAYER)) {
                g.drawImage(projectileFrames[currentFrame], (int) px - offset, (int) py -offset, drawSize, drawSize, null);
            }
            else {
                g.drawImage(enemyProjectileFrames[currentFrame], (int) px, (int) py, (TILE_SIZE), (TILE_SIZE), null);
            }
        }
    }

    BufferedImage loadSprite(String filePath) throws IOException {
        return ImageIO.read(new File(filePath));
    }
    public void paintCharacters(Graphics g) {
        List<Character> enemies = maze.getCurrentRoom().getEnemies();
        Position playerPos = maze.getPlayer().getPosition();
        int px = playerPos.getX() * TILE_SIZE;
        int py = playerPos.getY() * TILE_SIZE;
        int drawSize = (int) (TILE_SIZE * 1.7);
        int offset = (drawSize-TILE_SIZE)/2;
        g.drawImage(characterFrames[currentFrame],px - offset,py-offset, drawSize, drawSize, null);
        g.setColor(Color.RED);
        for (Character enemy : enemies) {
            Position enemyPos = enemy.getPosition();
            int enemyPosX = enemyPos.getX() * TILE_SIZE;
            int enemyPosY = enemyPos.getY() * TILE_SIZE;
            g.drawImage(enemyFrames[currentFrame], enemyPosX - offset, enemyPosY - offset, drawSize, drawSize, null);
        }

    }
    public void paintItems(Graphics g) {
        for (Weapon weapon : maze.getCurrentRoom().getWeapons()) {
            Position pos = weapon.getPosition();
            int px =  pos.getX() *TILE_SIZE;
            int py =   pos.getY() *TILE_SIZE;
            g.setColor(Color.BLACK);
            g.fillOval(px, py, TILE_SIZE/2, TILE_SIZE/2);
        }
    }

    public void updateFrames() {
        tick++;
        if (tick >= FRAME_SPEED) {
            tick = 0;
            currentFrame = (currentFrame + 1) % characterFrames.length;
        }
    }
    public int getTileSize() {
        return TILE_SIZE;
    }

}
