package com.burning8393.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private static final int SPEED = 10;
    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    public static final int HEIGHT = ResourceMgr.tankD.getWidth();

    private long nextChangeMovingTime = System.currentTimeMillis();
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
        randomMove();
        edgeDetect();
    }

    private void randomMove() {
        if (this.getGroup() == Group.GOOD) return;
        if (System.currentTimeMillis() < nextChangeMovingTime) {
            return;
        }
        int duration = r.nextInt(10000);
        nextChangeMovingTime = System.currentTimeMillis() + duration;
        int dirNum = r.nextInt(4);
        switch (dirNum) {
            case 0:
                dir = Dir.DOWN;
                break;
            case 1:
                dir = Dir.UP;
                break;
            case 2:
                dir = Dir.LEFT;
                break;
            case 3:
                dir = Dir.RIGHT;
                break;
        }
    }

    private void edgeDetect() {
        if (this.dir == Dir.UP && this.y <= TankFrame.UP_EDGE) {
            if (this.group == Group.GOOD) this.y = TankFrame.UP_EDGE;
            else this.dir = Dir.DOWN;
        } else if (this.dir == Dir.DOWN && (this.y + HEIGHT) >= TankFrame.GAME_HEIGHT) {
            if (this.group == Group.GOOD) this.y = TankFrame.GAME_HEIGHT - HEIGHT;
            else this.dir = Dir.UP;
        } else if (this.dir == Dir.LEFT && this.x <= TankFrame.LEFT_EDGE) {
            if (this.group == Group.GOOD) this.x = TankFrame.LEFT_EDGE;
            else this.dir = Dir.RIGHT;
        } else if (this.dir == Dir.RIGHT && (this.x + WIDTH) >= TankFrame.GAME_WIDTH) {
            if (this.group == Group.GOOD) this.x = TankFrame.GAME_WIDTH - WIDTH;
            else this.dir = Dir.LEFT;
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
