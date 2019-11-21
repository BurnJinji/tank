package com.burning8393.tank;

import java.awt.*;

public class Bullet extends GameObject {
    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");

    private int x, y;

    private Dir dir;

    private Group group;

    private boolean isAlive = true;

    private Rectangle rect = new Rectangle();

    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    public Bullet(int x, int y, Dir dir, Group group) {

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.rect.width = WIDTH;
        this.rect.height = HEIGHT;
        updateRect();
        GameModel.getInstance().add(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void paint(Graphics g) {
        if (!isAlive) {
            GameModel.getInstance().remove(this);
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
        updateRect();

        if (this.x < 0 || this.y < 0 || this.x > TankFrame.GAME_WIDTH || this.y > TankFrame.GAME_HEIGHT) {
            this.isAlive = false;
        }
    }

    public void die() {
        this.isAlive = false;
    }

    private void updateRect() {
        rect.x = this.x;
        rect.y = this.y;
    }

}
