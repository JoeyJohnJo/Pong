package framework.states;

import framework.KeyHandler;
import framework.entities.Ball;
import framework.entities.Player;
import framework.entities.Racket;

import java.awt.*;
import java.awt.event.KeyEvent;

import static framework.Frame.SCALED_HEIGHT;
import static framework.Frame.SCALED_WIDTH;

public class ScoreBoardState extends State {
    private Ball ball;
    private Racket player, enemy;
    private PlayingState playingState;
    private String winnerMessage = "";
    private KeyHandler keyHandler;
    public ScoreBoardState(StateMachine stateMachine, Ball ball, Racket player, Racket enemy, PlayingState playingState) {
        super("ScoreBoardState", stateMachine);
        keyHandler = new KeyHandler(stateMachine.getComponent());
        this.ball = ball;
        this.player = player;
        this.enemy = enemy;
        this.playingState = playingState;
    }

    @Override
    public void render(Graphics g) {
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString(playingState.getPlayerScore() + "   X   " + playingState.getEnemyScore(),
                SCALED_WIDTH/2 -  g.getFontMetrics(titleFont).stringWidth(playingState.getPlayerScore() + "   X   " + playingState.getEnemyScore()) /2, 60);
        g.setFont(titleFont.deriveFont(16f));
        g.setColor(Color.WHITE);
        g.drawString(winnerMessage, SCALED_WIDTH/2 - g.getFontMetrics(titleFont.deriveFont(16f)).stringWidth(winnerMessage)/2, 250);
        g.setFont(titleFont);
        g.drawString(String.valueOf(playingState.getTimer()/60), SCALED_WIDTH - g.getFontMetrics(titleFont).stringWidth(String.valueOf(playingState.getTimer()/60)) - 10, SCALED_HEIGHT);
    }
    public void freeze() {
        ball.stop();
        player.resetPosition();
        enemy.resetPosition();
    }

    @Override
    public void tick() {
        if (keyHandler.getPressedKeys().contains(KeyEvent.VK_SPACE)) {
            setNextState(stateMachine.getPreviousState());
            loadNextState();
            ball.start();
            playingState.resetTimer();
        }
    }
    void setWinnerMessage(String message) {
        winnerMessage = message;
    }
}
