package com.burning8393.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;

    private int x, y;

    private Dir dir;

    private boolean isAlive = true;

    private TankFrame tf = null;

    private static final int WIDTH = 20, HEIGHT = 20;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        System.out.println("paint");
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);
        move();

    }

    private void move() {
        if (!isAlive) {
            tf.bullets.remove(this);
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

        if (this.x < 0 || this.y < 0 || this.x > TankFrame.GAME_WIDTH || this.y > TankFrame.GAME_HEIGHT) {
            this.isAlive = false;
        }
    }
}
