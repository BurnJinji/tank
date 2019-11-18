package com.burning8393.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private static final int SPEED = 1;
    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    public static final int HEIGHT = ResourceMgr.tankD.getWidth();
    private static Random r = new Random();

    private int x, y;

    private Dir dir;

    private Group group;

    private boolean moving = true;

    private boolean isAlive = true;

    private TankFrame tf = null;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public Group getGroup() {
        return group;
    }

    public void paint(Graphics g) {
        if (!isAlive) {
            this.tf.enemies.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }

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

        if (r.nextInt(10) > 8 && this.getGroup() == Group.BAD) {
            this.fire();
        }


    }

    public void fire() {
        int bx =  x + WIDTH / 2 - Bullet.WIDTH / 2;
        int by =  y + HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bx, by, this.dir, this.group, tf));
    }

    public void die() {
        this.isAlive = false;
    }
}
