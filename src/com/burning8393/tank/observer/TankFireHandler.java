package com.burning8393.tank.observer;

import com.burning8393.tank.Tank;

public class TankFireHandler implements TankFireObserver {

    @Override
    public void actionOnFire(TankFireEvent e) {
        Tank tank = e.getSource();
        tank.fire();
    }
}
