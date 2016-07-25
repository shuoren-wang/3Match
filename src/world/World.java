package world;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import entity.Entity;
import matchgame.Game;
import utils.Utils;

import java.awt.*;

/**
 * Created by shuorenwang on 2016-07-20.
 */
public class World {
    private int width, height;  //number of tiles
    //   private int x,y;
    private Game game;
    private int[][] boardIDs;
    private Entity[][] boardEntities;

    private Entity currentEntity;
    private boolean pressed;

    public World(Game game, String path) {
        this.game = game;

        loadWorld(path);

        boardEntities = new Entity[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boardEntities[x][y] = new Entity(x * Entity.WIDTH, y * Entity.HEIGHT, boardIDs[x][y]);
                System.out.printf(boardEntities[x][y].getX() + " ");
            }
            System.out.println();
        }
    }

    public void tick() {
        int x = game.getMouseManager().getMouseX();
        int y = game.getMouseManager().getMouseY();
        if (game.getMouseManager().isPressed() == true && x <= width * Entity.WIDTH
                && y <= height * Entity.HEIGHT) {
//            for (int y = 0; y < height; y++)
//                for (int x = 0; x < width; x++) {
//
//                    if (boardEntities[x][y].getBounds().contains(game.getMouseManager().getMouseX(), game.getMouseManager().getMouseY())) {
//                        System.out.println("Receive Press true");
//                        currentEntity = boardEntities[x][y];
//                        System.out.println("Entity:x=" + currentEntity.getX() + "y=" + currentEntity.getY());
//                        pressed = true;
//                        return;
//                    }


            System.out.println(" x=" +  game.getMouseManager().getMouseX() + " y=" + game.getMouseManager().getMouseY());
            currentEntity = boardEntities[x / Entity.WIDTH][y / Entity.HEIGHT];
            if(currentEntity.getBounds().contains(game.getMouseManager().getMouseX(),game.getMouseManager().getMouseY()))
                System.out.println("Contain");

            System.out.println("Mouse Position: x=" + game.getMouseManager().getMouseX() + " y=" + game.getMouseManager().getMouseY());

            System.out.println("Current Position: x=" + currentEntity.getX() + " y=" + currentEntity.getY());

//            if (game.getMouseManager().left)
//                swapLeft(x, y);
//            else if (game.getMouseManager().right)
//                swapRight(x, y);
//            else if (game.getMouseManager().up)
//                swapUp(x, y);
//            else if (game.getMouseManager().down)
//                swapDown(x, y);


            //set pressed=false
            game.getMouseManager().setPressed(false);
            pressed = false;

        }
    }

    public void render(Graphics g) {


        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                getEntity(x, y).render(g, x * Entity.WIDTH, y * Entity.HEIGHT);
            }

        g.fillRect(game.getMouseManager().getMouseX(), game.getMouseManager().getMouseY(), 10, 10);//////////////////////////////////////

        if (currentEntity != null) {
            g.fillRect(currentEntity.getX(), currentEntity.getY(), Entity.WIDTH, Entity.HEIGHT);
            // System.out.println("Rendered Entity id="+currentEntity.getId());
//            System.out.println(" Render: x=" +  game.getMouseManager().getMouseX() + " y=" + game.getMouseManager().getMouseY());
        }


    }

    /**
     * exchange the position of currentEntity with its left
     */
    private void swapLeft(int x, int y) {
        if (currentEntity.getX() < 0)
            return;

        boardEntities[x][y] = boardEntities[x - 1][y];
        boardEntities[x - 1][y] = currentEntity;

        boardIDs[x][y] = boardEntities[x][y].getId();
        boardIDs[x - 1][y] = boardEntities[x - 1][y].getId();
    }

    /**
     * exchange the position of currentEntity with its right
     */
    private void swapRight(int x, int y) {
        if (currentEntity.getX() > width)
            return;

        boardEntities[x][y] = boardEntities[x + 1][y];
        boardEntities[x + 1][y] = currentEntity;


        boardIDs[x][y] = boardEntities[x][y].getId();
        boardIDs[x + 1][y] = boardEntities[x + 1][y].getId();
    }


    /**
     * exchange the position of currentEntity with the entity above
     */
    private void swapUp(int x, int y) {
        if (currentEntity.getY() < 0)
            return;

        boardEntities[x][y] = boardEntities[x][y - 1];
        boardEntities[x][y - 1] = currentEntity;


        boardIDs[x][y] = boardEntities[x][y].getId();
        boardIDs[x][y - 1] = boardEntities[x][y - 1].getId();
    }

    /**
     * exchange the position of currentEntity with the entity below
     */
    private void swapDown(int x, int y) {
        if (currentEntity.getX() > width)
            return;

        boardEntities[x][y] = boardEntities[x][y + 1];
        boardEntities[x][y + 1] = currentEntity;

        boardIDs[x][y] = boardEntities[x][y].getId();
        boardIDs[x][y + 1] = boardEntities[x][y + 1].getId();
    }



    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");

        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
//        x=Utils.parseInt(tokens[2]);
//        y=Utils.parseInt(tokens[3]);

        boardIDs = new int[width][height];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                boardIDs[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
    }


    /**
     * if (x,y) is outside the map, return grass,
     * otherwise, return the corresponding entity
     *
     * @param x index of tile on the x direction (column)
     * @param y index of tile on the y direction (row)
     * @return entity
     */
    public Entity getEntity(int x, int y) {

        if (boardEntities[x][y] == null) {

            return new Entity(x * Entity.WIDTH, y * Entity.HEIGHT, 4);
        }
        return boardEntities[x][y];
    }

}
