package framework.states;

import framework.KeyHandler;
import java.awt.*;
import java.awt.event.KeyEvent;
import static framework.Frame.*;

public class TitleScreen extends State {
    private String start = "PRESS SPACE TO START";
    private int frames; // For blinking start message;
    private KeyHandler keyHandler;

    public TitleScreen(StateMachine stateMachine) {
        super("Title Screen", stateMachine);
        setNextState(new PlayingState("Playing State", stateMachine));
        keyHandler = new KeyHandler(stateMachine.getComponent());
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, SCALED_WIDTH, SCALED_HEIGHT);
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString("PONG", SCALED_WIDTH/2 - g.getFontMetrics(titleFont).stringWidth("pong") /2, 60);
        g.setFont(titleFont.deriveFont(16f));
        g.drawString(start, SCALED_WIDTH/2 - g.getFontMetrics(titleFont.deriveFont(16f)).stringWidth(start) /2, 250);
    }

    @Override
    public void tick() {
        frames++;
        if (frames > 30) {
            start = "PRESS SPACE TO START";
        }
        if (frames > 80) {
            frames = 0;
            start = "";
        }

        if (keyHandler.getPressedKeys().contains(KeyEvent.VK_SPACE)) {
            System.out.println("Space pressed");
            stateMachine.getComponent().removeKeyListener(keyHandler);
            loadNextState();
        }
    }
}
