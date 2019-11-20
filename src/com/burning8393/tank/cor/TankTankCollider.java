package com.burning8393.tank.cor;

import com.burning8393.tank.GameObject;
import com.burning8393.tank.Tank;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {

        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank tankA = (Tank) o1;
            Tank tankB = (Tank) o2;
            if (tankA.getRect().intersects(tankB.getRect())) {
                tankA.backPos();
                tankB.backPos();
            }

        }
        return true;
    }
}
