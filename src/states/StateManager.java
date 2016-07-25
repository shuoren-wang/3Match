package states;

import matchgame.Game;

/**
 * Created by shuorenwang on 2016-07-24.
 */
public class StateManager {
    private static State currentState=null;
    private Game game;

    public StateManager(Game game){
        currentState=null;
        this.game=game;
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State state) {
        currentState = state;
    }
}
