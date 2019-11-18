package com.burning8393.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 30;

    private int x, y;

    private Dir dir;

    private Group group;

    private boolean isAlive = true;

    private TankFrame tf = null;

    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {

        this.x = x;
        this.y = y ;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public Group getGroup() {
        return group;
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

    public void collideWith(Tank tank) {
        if (getGroup() == tank.getGroup()) return;
        Rectangle rect1 = new Rectangle(this.x, this.y, this.WIDTH, this.HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect1.intersects(rect2)) {
            this.die();
            tank.die();
            tf.explodes.add(new Explode(tank.getX(), tank.getY(), tf));
        }
    }

    private void die() {
        this.isAlive = false;
    }
}
