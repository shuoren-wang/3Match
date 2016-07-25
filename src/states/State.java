package states;

import matchgame.Game;

import java.awt.*;

/**
 * Created by shuorenwang on 2016-07-20.
 */
public abstract class State {

    protected Game game;

    public State(Game game){
        this.game=game;
    }

    public abstract void tick();

    public abstract void render( Graphics g);

}
