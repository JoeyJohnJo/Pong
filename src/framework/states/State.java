package framework.states;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public abstract class State {
    protected String name;
    protected StateMachine stateMachine;
    protected State nextState;
    protected Font titleFont;
    protected Font subFont;
    public State(String name, StateMachine stateMachine) {
        this.name = name;
        this.stateMachine = stateMachine;
        this.stateMachine.addState(this);
        registerFont();
        subFont = titleFont.deriveFont(16f);
    }
    public State(String name, StateMachine stateMachine, State nextState) {
        this.name = name;
        this.stateMachine = stateMachine;
        this.stateMachine.addState(this);
        this.nextState = nextState;
    }
    public abstract void render(Graphics g);
    public abstract void tick();

    @Override
    public String toString() {
        return name;
    }
    public void setNextState(State nextState) {
        this.nextState = nextState;
    }
    public State getNextState() {return nextState;}
    protected void loadNextState() {
        stateMachine.setCurrentState(nextState);
    }
    private void registerFont() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        InputStream file = getClass().getResourceAsStream("/framework/fonts/bit5x5.ttf");
        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(48f);
            ge.registerFont(titleFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
