package com.burning8393.tank;

import com.burning8393.tank.fire.FireStrategy;
import com.burning8393.tank.observer.TankFireEvent;
import com.burning8393.tank.observer.TankFireHandler;
import com.burning8393.tank.observer.TankFireObserver;

import java.awt.*;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class Tank extends GameObject {
    private static final int SPEED = PropertyMgr.getInt("tankSpeed");
    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getWidth();

    private static Random r = new Random();

    private int preX, preY;

    private Dir dir;

    private Group group;

    private volatile boolean moving = true;

    private boolean isAlive = true;

    private Rectangle rect = new Rectangle();

    private FireStrategy fs;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.rect.width = WIDTH;
        this.rect.height = HEIGHT;

        try {
            if (this.group == Group.GOOD) {
                String goodFS = (String) PropertyMgr.get("goodFS");
                this.fs = (FireStrategy) Class.forName(goodFS).newInstance();
            } else {
                String badFS = (String) PropertyMgr.get("badFS");
                this.fs = (FireStrategy) Class.forName(badFS).newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        updateRect();
        GameModel.getInstance().add(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
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

    public void paint(Graphics g) {
        if (!isAlive) {
            GameModel.getInstance().remove(this);
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

    public void backPos() {
        x = preX;
        y = preY;
    }

    private void move() {
        if (!isMoving()) {
            return;
        }
        preX = x;
        preY = y;
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

    private List<TankFireObserver> observers = Arrays.asList(new TankFireHandler());

    public void emitFireEvent() {
        TankFireEvent tankFireEvent = new TankFireEvent(this);
        for (TankFireObserver observer : observers) {
            observer.actionOnFire(tankFireEvent);
        }
    }
}
