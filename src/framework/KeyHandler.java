package framework;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyHandler implements KeyListener {

    private ArrayList<Integer> pressedKeys;
    private Component component;
    public KeyHandler(Component c) {
        pressedKeys = new ArrayList<>();
        component = c;
        component.addKeyListener(this);
    }

    public ArrayList<Integer> getPressedKeys() {
        return pressedKeys;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedKeys.contains(e.getKeyCode())) pressedKeys.add( e.getKeyCode());
        System.out.println(pressedKeys);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove((Integer) e.getKeyCode());
    }
}
