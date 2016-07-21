package world;

import java.awt.*;

/**
 * Created by shuorenwang on 2016-07-20.
 */
public class World {
    private int width, height;
    private int [][]tiles;

    public World(){

    }

    public void tick(){}

    public void render(Graphics g){

        tiles=new int[width][height];

        for(int y=0; y<height; y++)
            for(int x=0; x<width; x++){
                tiles[y][x]=0;
            }
    }

    private void loadWorld(String path){
        width=5;
        height=5;
        tiles=new int[width][height];

        for(int y=0; y<height; y++)
            for(int x=0; x<width; x++){
                tiles[y][x]=0;
            }

    }

}
