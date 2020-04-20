package framework.states;

import framework.entities.Ball;
import framework.entities.Enemy;
import framework.entities.Player;
import framework.entities.Racket;

import java.awt.*;

import static framework.Frame.SCALED_HEIGHT;
import static framework.Frame.SCALED_WIDTH;

public class PlayingState extends State {
    private Racket player;
    private Racket enemy;
    private Ball ball;
    private int playerScore = 0;
    private int enemyScore = 0;
    private int timer = 0;
    private static boolean leftScored, rightScored;
    private ScoreBoardState nextState;
    public PlayingState(String name, StateMachine stateMachine) {
        super(name, stateMachine);
        ball = new Ball();
        player = new Player(stateMachine.getComponent());
        enemy = new Enemy(ball);
        ball.setLeft(player);
        ball.setRight(enemy);
        enemy.setPositionX(SCALED_WIDTH - enemy.getWidth());
        nextState = new ScoreBoardState(stateMachine, ball, player, enemy, this);
        setNextState(nextState);
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
        enemy.render(g);
        ball.render(g);
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString(playerScore + "   X   " + enemyScore, SCALED_WIDTH/2 -  g.getFontMetrics(titleFont).stringWidth(playerScore + "   X   " + enemyScore) /2, 60);
        g.drawString(String.valueOf(timer/60), SCALED_WIDTH - g.getFontMetrics(titleFont).stringWidth(String.valueOf(timer/60)) - 10, SCALED_HEIGHT);
    }

    @Override
    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
        scored();
        timer++;
    }

    private void scored() {
        if (leftScored) {
            playerScore++;
            nextState.setWinnerMessage("PLAYER SCORED!");
            loadNextState();
            nextState.freeze();
            leftScored = false;
        }
        else if (rightScored) {
            enemyScore++;
            nextState.setWinnerMessage("ENEMY SCORED!");
            loadNextState();
            nextState.freeze();
            rightScored = false;
        }
    }
    public int getPlayerScore() {
        return playerScore;
    }
    public int getEnemyScore() {
        return enemyScore;
    }
    public int getTimer() {
        return timer;
    }
    public void resetTimer() {
        timer = 0;
    }
    public static void setLeftScored(){
        leftScored = true;
    }
    public static void setRightScored(){
        rightScored = true;
    }
}
