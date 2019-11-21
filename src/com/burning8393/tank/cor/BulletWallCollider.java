package com.burning8393.tank.cor;

import com.burning8393.tank.*;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall) o2;
            if (bullet.getRect().intersects(wall.getRect())) {
                bullet.die();
                int eX = bullet.getX() - Bullet.WIDTH / 2;
                int eY = bullet.getY() - Bullet.HEIGHT / 2;
                new Explode(eX, eY);
            }
            return true;
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        } else {
            return true;
        }
    }
}
