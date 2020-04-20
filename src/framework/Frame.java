package framework;

import framework.entities.Player;
import framework.states.State;
import framework.states.StateMachine;
import framework.states.TitleScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Frame extends Canvas implements Runnable {

    public static JFrame frame;
    private StateMachine stateMachine;
    private TitleScreen titleScreen;
    private static final int WIDTH = 160;
    private static final int HEIGHT = 120;
    private static final int SCALE = 4;
    public static final int SCALED_WIDTH = WIDTH*SCALE;
    public static final int SCALED_HEIGHT = HEIGHT*SCALE;

    private Thread thread;
    private boolean running;

    public Frame() {
        initFrame();
        stateMachine = new StateMachine(this);
        titleScreen = new TitleScreen(stateMachine);
        stateMachine.setCurrentState(titleScreen);
    }

    private void initFrame() {
        this.setPreferredSize(new Dimension(SCALED_WIDTH, SCALED_HEIGHT));
        frame = new JFrame("PONG");
        frame.add(this);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop() {

    }

    public void tick() {
        requestFocus();
        stateMachine.getCurrentState().tick();
    }
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        stateMachine.getCurrentState().render(g);
        bs.show();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,SCALED_WIDTH, SCALED_HEIGHT);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                frames ++;
                delta--;
            }
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer+=1000;
                System.out.println(stateMachine.getCurrentState().toString());
            }
        }
    }
}
