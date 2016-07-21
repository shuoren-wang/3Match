package matchgame;

import display.Display;
import gfx.Assets;
import world.World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


/**
 * Created by shuorenwang on 2016-07-20.
 */
public class Game implements Runnable{

    private String title;
    private int width, height;

    private Display display;
    private World world;

    private Thread thread;
    private boolean running =false;

    private BufferStrategy bufferStrategy;
    private Graphics g;


    public Game(String title, int width, int height){
        this.title=title;
        this.width=width;
        this.height=height;
    }

    private void init(){
        display=new Display(title, width, height);

        Assets.init();
        world=new World();
    }

    @Override
    public void run() {

        init();

        while (running){
            tick();
            render();
        }

        stop();
    }

    public synchronized void start(){
        if(running)
            return;
        running=true;

        thread=new Thread(this);    //run this class on thread
        thread.start();
    }


    public synchronized void stop(){
        if(!running)
            return;
        running=false;

        try{
            thread.join(); //close thread
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }


    private void tick(){}

    private void render(){
        bufferStrategy=display.getCanvas().getBufferStrategy();

        if(bufferStrategy==null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g=bufferStrategy.getDrawGraphics();
        //Clear Screen
        g.clearRect(0,0,width,height);

        //Draw Here


        //End

        bufferStrategy.show();
        g.dispose();
    }

}
