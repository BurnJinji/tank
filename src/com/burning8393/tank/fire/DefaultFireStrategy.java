package com.burning8393.tank.fire;

import com.burning8393.tank.Audio;
import com.burning8393.tank.Bullet;
import com.burning8393.tank.Group;
import com.burning8393.tank.Tank;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx =  tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by =  tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        new Bullet(bx, by, tank.getDir(), tank.getGroup(), tank.getGm());
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
