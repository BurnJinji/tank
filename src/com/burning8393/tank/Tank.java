package com.burning8393.tank;

import java.awt.*;

public class Tank {
    private static final int SPEED = 5;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    private int x, y;

    private Dir dir;

    private boolean moving = false;

    private TankFrame tf = null;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        System.out.println("paint");
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(c);
        move();

    }

    private void move() {
        if (!isMoving()) {
            return;
        }
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }

    public void fire() {
        int xBulletOffSet = WIDTH / 2;
        int yBulletOffSet = WIDTH / 2;
        tf.bullets.add(new Bullet(this.x + xBulletOffSet, this.y + yBulletOffSet, this.dir, tf));
    }
}
