package com.burning8393.tank.abstractfactory;

import com.burning8393.tank.*;

public class DefaultGameFactory extends GameFactory {
    private static final DefaultGameFactory instance = new DefaultGameFactory();

    private DefaultGameFactory() {
    }

    public static DefaultGameFactory getInstance() {
        return instance;
    }

    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank(x, y, dir, group, tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Bullet(x, y, dir, group, tf);
    }
}
