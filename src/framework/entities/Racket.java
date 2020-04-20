package framework.entities;

import framework.Frame;

import java.awt.*;

public abstract class Racket extends Entity {
    public Racket(String name) {
        super(name, 10, 60);
        setPositionY(Frame.SCALED_HEIGHT/2 - height/2);
        speed = 5;
    }

    @Override
    public void tick() {
        move();
    }
    protected abstract void move();
    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(positionX, positionY, width, height);
    }
    protected int isAtBorder() {
        if (positionY <= 0) return 0; // Top of screen
        if (positionY + height >= Frame.SCALED_HEIGHT) return 1; //Bottom of screen

        return 2;
    }
    public void resetPosition() {
        setPositionY(Frame.SCALED_HEIGHT/2 - height/2);
    }

}
