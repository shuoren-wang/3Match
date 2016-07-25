package world;

import matchgame.Game;
import tile.Tile;
import utils.Utils;

import java.awt.*;

/**
 * Created by shuorenwang on 2016-07-20.
 */
public class World {
    private int width, height;  //number of tiles
 //   private int x,y;
    private Game game;
    private int[][] boardTiles;

    public World(Game game, String path){
        this.game=game;

        loadWorld(path);
    }

    public void tick(){}

    public void render(Graphics g){

        for(int y=0; y<height; y++)
            for(int x=0; x<width; x++){
                getTile(x,y).render(g,x*Tile.WIDTH,y*Tile.HEIGHT);
            }
    }

    private void loadWorld(String path){
        String file= Utils.loadFileAsString(path);
        String[] tokens=file.split("\\s+");

        width=Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
//        x=Utils.parseInt(tokens[2]);
//        y=Utils.parseInt(tokens[3]);

        boardTiles=new int[width][height];
        for (int y=0; y<height ;y++)
            for(int x=0; x<width; x++){
                boardTiles[x][y]=Utils.parseInt(tokens[(x+y*width)+4]);
            }
    }


    public Tile getTile(int x, int y){
        Tile t=Tile.tiles[boardTiles[x][y]];

        if(t==null)
            t=Tile.dirtTile;

        return t;
    }

}
