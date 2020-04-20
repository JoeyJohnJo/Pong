package framework.entities;

import java.awt.Graphics;

public abstract class Entity {
    protected String name;
    protected int positionX, positionY;
    protected int width, height;
    protected double speed;

    public Entity(String name) {
        this.name = name;
        positionX = 0;
        positionY = 0;
        width = 0;
        height = 0;
        speed = 0;
    }
    public Entity(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        positionX = 0;
        positionY = 0;
        speed = 0;
    }
    public Entity(String name, int width, int height, int x, int y) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.positionX = x;
        this.positionY = y;
        speed = 0;
    }

    public int getX() {
        return positionX;
    }
    public int getY() {
        return positionY;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public String getName() {
        return name;
    }

    public void setPositionX(int x) {
        positionX = x;
    }
    public void setPositionY(int y) {
        positionY = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    protected void setSpeed(int speed) {
        this.speed = speed;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
