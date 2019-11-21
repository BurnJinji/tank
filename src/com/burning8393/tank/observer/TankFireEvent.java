package com.burning8393.tank.observer;

import com.burning8393.tank.Tank;

public class TankFireEvent {
    private Tank tank;

    public Tank getSource() {
        return tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }
}
