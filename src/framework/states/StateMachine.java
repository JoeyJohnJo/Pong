package framework.states;

import java.awt.*;
import java.util.ArrayList;

public class StateMachine {
    private ArrayList<State> states;
    private State currentState;
    private State previousState;
    private Component component;
    public StateMachine(Component component){
        this.component = component;
        states = new ArrayList<>();
        currentState = null;
        previousState = null;
    }

    public void addState(State state) {
        this.states.add(state);
    }

    public State getCurrentState() {return currentState;}
    public State getPreviousState() {return previousState;}
    public void setCurrentState(State currentState) {
        previousState = this.currentState;
        this.currentState = currentState;
    }
    public Component getComponent() {return component;}
}
