package matchgame;

import display.Display;
import gfx.Assets;
import input.MouseManager;
import states.GameState;
import states.MenuState;
import states.State;
import states.StateManager;
import world.World;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;


/**
 * Created by shuorenwang on 2016-07-20.
 */
public class Game implements Runnable{

    private String title;
    private int width, height;

    private Display display;

    private Thread thread;
    private boolean running =false;

    private BufferStrategy bufferStrategy;
    private Graphics g;

    private World world;

    //States
    private State gameState;
    private State menuState;

    //input
    private MouseManager mouseManager;


    public Game(String title, int width, int height){
        this.title=title;
        this.width=width;
        this.height=height;
        mouseManager=new MouseManager();
    }

    private void init(){
        display=new Display(title, width, height);
        display.getCanvas().addMouseListener(mouseManager);
        display.addMouseMotionListener(mouseManager);
        display.addMouseListener(mouseManager);
        display.addMouseMotionListener(mouseManager);

        Assets.init();
        world=new World(this, "res/level1.txt");
//      world=new World(this);


        gameState=new GameState(this);
        menuState=new MenuState(this);

        StateManager.setCurrentState(gameState);

    }

    @Override
    public void run() {

        init();

        while (running){
            tick();
            render();
        }

    }

    public synchronized void start(){
        if(running)
            return;
        running=true;

        thread=new Thread(this);    //run this class on thread
        thread.start();
    }



    private void tick(){
        if(StateManager.getCurrentState()!=null)
            StateManager.getCurrentState().tick();
    }

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

        if(StateManager.getCurrentState()!=null)
            StateManager.getCurrentState().render(g);

   //     g.fillRect(getMouseManager().getMouseX(), getMouseManager().getMouseY(), 10, 10);//////////////////////////////////////


        //End

        bufferStrategy.show();
        g.dispose();
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }
}
