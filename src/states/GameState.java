package states;

import matchgame.Game;
import world.World;

import java.awt.*;

/**
 * Created by shuorenwang on 2016-07-20.
 */
public class GameState extends State {

    private World world;

    public GameState(Game game) {
        super(game);
        world=new World(game, "res/level1.txt");

    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
