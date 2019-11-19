package com.burning8393.tank.abstractfactory;

import com.burning8393.tank.*;

import java.awt.*;

public class RectBullet extends BaseBullet {
    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");

    private int x, y;

    private Dir dir;

    private Group group;

    private boolean isAlive = true;

    private Rectangle rect = new Rectangle();

    private TankFrame tf;

    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    public RectBullet(int x, int y, Dir dir, Group group, TankFrame tf) {

        this.x = x;
        this.y = y ;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        this.rect.width = WIDTH;
        this.rect.height = HEIGHT;
        updateRect();
        tf.bullets.add(this);
    }

    public Group getGroup() {
        return group;
    }

    public void paint(Graphics g) {
        if (!isAlive) {
            tf.bullets.remove(this);
        }

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 20, 20);
        g.setColor(c);

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

    public void collideWith(BaseTank tank) {
        if (getGroup() == tank.getGroup()) return;
        if (rect.intersects(tank.getRect())) {
            this.die();
            tank.die();
            int eX =  tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY =  tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tf.explodes.add(tf.gf.createExplode(eX, eY, tf));
        }
    }

    private void die() {
        this.isAlive = false;
    }

    private void updateRect() {
        rect.x = this.x;
        rect.y = this.y;
    }
}
