package com.burning8393.tank.fire;

import com.burning8393.tank.*;
import com.burning8393.tank.decorator.RectGODecorator;
import com.burning8393.tank.decorator.TailGODecorator;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx =  tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by =  tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        GameModel.getInstance().add(new RectGODecorator(new TailGODecorator(new Bullet(bx, by, tank.getDir(), tank.getGroup()))));
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
