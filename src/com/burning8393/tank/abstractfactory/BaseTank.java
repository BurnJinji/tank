package com.burning8393.tank.abstractfactory;

import com.burning8393.tank.Group;

import java.awt.*;

public abstract class BaseTank {

    public abstract void paint(Graphics g);

    public abstract void die();

    public abstract Group getGroup();

    public abstract Rectangle getRect();

    public abstract int getX();

    public abstract int getY();
}
