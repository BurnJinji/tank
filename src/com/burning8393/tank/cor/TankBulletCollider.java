package com.burning8393.tank.cor;

import com.burning8393.tank.*;

public class TankBulletCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            if (bullet.getGroup() == tank.getGroup()) return true;
            if (bullet.getRect().intersects(tank.getRect())) {
                bullet.die();
                tank.die();
                int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                tank.getGm().objects.add(new Explode(eX, eY, tank.getGm()));
            }
            return false;
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        } else {
            return true;
        }
    }
}
