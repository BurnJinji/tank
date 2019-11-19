package com.burning8393.tank;

import com.burning8393.tank.abstractfactory.BaseTank;
import com.burning8393.tank.fire.FireStrategy;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Tank extends BaseTank {
    private static final int SPEED = PropertyMgr.getInt("tankSpeed");
    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getWidth();

    private static Random r = new Random();

    private int x, y;

    private Dir dir;

    private Group group;

    private volatile boolean moving = true;

    private boolean isAlive = true;

    private Rectangle rect = new Rectangle();

    private TankFrame tf;

    private FireStrategy fs;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        this.rect.width = WIDTH;
        this.rect.height = HEIGHT;

        try {
            if (this.group == Group.GOOD) {
                String goodFS = (String) PropertyMgr.get("goodFS");
                this.fs = (FireStrategy) Class.forName(goodFS).getDeclaredConstructor().newInstance();
            } else {
                String badFS = (String) PropertyMgr.get("badFS");
                this.fs = (FireStrategy) Class.forName(badFS).getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        updateRect();
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

    public Rectangle getRect() {
        return rect;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void paint(Graphics g) {
        if (!isAlive) {
            this.tf.enemies.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
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

        if (r.nextInt(100) > 95 && this.getGroup() == Group.BAD) {
            this.fire();
        }
        if (r.nextInt(100) > 95 && this.getGroup() == Group.BAD) {
            randomMove();
        }

        boundCheck();

        updateRect();
    }

    private void randomMove() {
        dir = Dir.values()[r.nextInt(4)];
    }

    private void boundCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    public void fire() {
        fs.fire(this);
    }

    public void die() {
        this.isAlive = false;
    }

    private void updateRect() {
        rect.x = this.x;
        rect.y = this.y;
    }

}
