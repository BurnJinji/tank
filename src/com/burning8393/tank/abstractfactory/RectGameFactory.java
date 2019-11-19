package com.burning8393.tank.abstractfactory;

import com.burning8393.tank.Dir;
import com.burning8393.tank.Group;
import com.burning8393.tank.TankFrame;

public class RectGameFactory extends GameFactory {
    private static final GameFactory INSTANCE = new RectGameFactory();

    private RectGameFactory() {
    }

    public static GameFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectTank(x, y, dir, group, tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectBullet(x, y, dir, group, tf);
    }
}
