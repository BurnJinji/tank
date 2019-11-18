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
        int selfXOffSet = WIDTH / 2;
        int selfYOffSet = HEIGHT / 2;

        this.x = x - selfXOffSet;
        this.y = y - selfYOffSet;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!isAlive) {
            tf.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();

    }

    private void move() {
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
