package framework.entities;

import framework.Frame;

public class Enemy extends Racket {
    private Ball ball;
    public Enemy(Ball ball) {
        super("Enemy");
        this.ball = ball;
    }

    @Override
    protected void move() {
        positionY = ball.positionY  - height/2;
        if (isAtBorder() == 0) positionY = 0;
        if (isAtBorder() == 1) positionY = Frame.SCALED_HEIGHT - height;
    }
}
