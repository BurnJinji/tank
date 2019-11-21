package com.burning8393.tank.fire;

import com.burning8393.tank.*;

public class AllDirsFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx =  tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by =  tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        for (Dir dir : Dir.values()) {
            new Bullet(bx, by, dir, tank.getGroup());
        }
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
