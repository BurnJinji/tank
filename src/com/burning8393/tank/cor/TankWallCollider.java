package com.burning8393.tank.cor;

import com.burning8393.tank.GameObject;
import com.burning8393.tank.Tank;
import com.burning8393.tank.Wall;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;
            if (tank.getRect().intersects(wall.getRect())) {
                tank.backPos();
            }
            return true;

        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            return collide(o2, o1);
        }
        return true;
    }
}
