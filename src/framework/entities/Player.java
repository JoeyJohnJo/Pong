package framework.entities;

import framework.KeyHandler;
import java.awt.Component;
import java.awt.event.KeyEvent;

public class Player extends Racket{
    private KeyHandler keyHandler;

    public Player(Component c) {
        super("Player");
        keyHandler = new KeyHandler(c);

    }

    @Override
    protected void move() {
        if (keyHandler.getPressedKeys().contains(KeyEvent.VK_RIGHT) || keyHandler.getPressedKeys().contains(KeyEvent.VK_DOWN ) ||
                keyHandler.getPressedKeys().contains(KeyEvent.VK_D) || keyHandler.getPressedKeys().contains(KeyEvent.VK_S))
        {
            if (isAtBorder() != 1) positionY+=speed;
        }
        if (keyHandler.getPressedKeys().contains(KeyEvent.VK_LEFT) || keyHandler.getPressedKeys().contains(KeyEvent.VK_UP ) ||
                keyHandler.getPressedKeys().contains(KeyEvent.VK_A) || keyHandler.getPressedKeys().contains(KeyEvent.VK_W))
        {
            if (isAtBorder() != 0) positionY -= speed;
        }
    }
}
