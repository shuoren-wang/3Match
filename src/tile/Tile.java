package tile;

import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by shuorenwang on 2016-07-20.
 */
public class Tile {

    public static final int WIDTH=60,HEIGHT=60;
    public static Tile[] tiles=new Tile[50];
    public static Tile grassTile=new Tile(Assets.grass,0);
    public static Tile dirtTile=new Tile(Assets.dirt,1);
    public static Tile stoneTile=new Tile(Assets.stone,2);
    public static Tile wallTile=new Tile(Assets.wall,3);
    public static Tile treeTile=new Tile(Assets.tree,4);
    public static Tile lizardTile=new Tile(Assets.lizard,5);


    private BufferedImage texture;
    private int id;

    public Tile(BufferedImage texture, int id){
        this.texture=texture;
        this.id=id;

        tiles[id]=this;
    }

    public void tick(){}

    public void render(Graphics g, int x, int y){
        g.drawImage(texture,x,y,WIDTH,HEIGHT,null);
    }


    //GETTER and SETTER
    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public int getId() {
        return id;
    }
}
