package framework.entities;

import framework.Frame;
import framework.states.PlayingState;

import java.awt.*;
import java.util.Random;

public class Ball extends Entity{
    private Racket right, left;
    private double dx, dy;
    int innerBound  = 40;
    int outerBound = 50;
    private double maxSpeed =     150.0f;
    private double initialSpeed = 5.0f;
    public Ball() {
        super("Ball Entity");
        width = 10;
        height = 10;
        positionX = Frame.SCALED_WIDTH/2 - width/2;
        positionY = Frame.SCALED_HEIGHT/2 - height/2;
        speed = initialSpeed;
        int angle = new Random().nextInt(innerBound) + outerBound;
        dx = Math.sin(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
    }
    public void stop() {
        positionX = Frame.SCALED_WIDTH/2 - width/2;
        positionY = Frame.SCALED_HEIGHT/2 - height/2;
        speed = 0;
        left.speed = 5;
        right.speed = 5;
    }
    public void start() {
        speed = initialSpeed;
        int angle = new Random().nextInt(innerBound) + outerBound;
        if (dx > 0)
            dx = -Math.sin(Math.toRadians(angle));
        else if (dx < 0)
            dx = Math.sin(Math.toRadians(angle));
        if (Math.random() > 0.5)
            dy = Math.cos(Math.toRadians(new Random().nextInt(innerBound) + outerBound));
        else dy = -Math.cos(Math.toRadians(new Random().nextInt(innerBound) + outerBound));
    }
    @Override
    public void tick() {
        try {
            if (positionX < -speed) PlayingState.setRightScored();
            if (positionX > right.positionX + width + speed ) PlayingState.setLeftScored();
        } finally {
            if (isCollidingRacketR()) {
                int angle = new Random().nextInt(innerBound) + outerBound;
                dx = Math.sin(Math.toRadians(angle));
                if (Math.random() > 0.5)
                    dy = Math.cos(Math.toRadians(new Random().nextInt(innerBound) + outerBound));
                else dy = -Math.cos(Math.toRadians(new Random().nextInt(innerBound) + outerBound));
                dx*=-1;
                speedUp();
            }
            if (isCollidingRacketL()) {
                int angle = new Random().nextInt(innerBound) + outerBound;
                dx = -Math.sin(Math.toRadians(angle));
                if (Math.random() > 0.5)
                    dy = Math.cos(Math.toRadians(new Random().nextInt(innerBound) + outerBound));
                else dy = -Math.cos(Math.toRadians(new Random().nextInt(innerBound) + outerBound));
                dx*=-1;
                speedUp();
            }

            if (positionY <= 0.00f) dy = Math.cos(Math.toRadians(new Random().nextInt(innerBound) + outerBound));
            if (positionY + height >= Frame.SCALED_HEIGHT) dy*= -1;

            positionX += dx * speed;
            positionY += dy * speed;
        }
     }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(positionX, positionY, width, height);
    }

    private boolean isCollidingRacketR() {
        return (positionY >= right.getY() && positionY <= right.getY() + right.getHeight()) &&
                positionX + width >= Frame.SCALED_WIDTH - left.getWidth();

    }
    private boolean isCollidingRacketL() {
        return (positionY >= left.getY() && positionY <= left.getY() + left.getHeight()) &&
                positionX <= left.getWidth();
    }

    private void speedUp() {
        if (speed < maxSpeed) {
            speed+= 0.2f;
            left.speed+=0.2f;
            right.speed+=0.2f;
        }
    }
    public void setLeft(Racket left) {
        this.left = left;
    }
    public void setRight(Racket right) {
        this.right = right;
    }
}
