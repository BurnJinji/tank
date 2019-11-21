package com.burning8393.tank.decorator;

import com.burning8393.tank.GameObject;
import com.burning8393.tank.TankFrame;

import java.awt.*;

public abstract class GODecorator extends GameObject {

    GameObject go;

    protected boolean isAlive = true;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public abstract void paint(Graphics g);

    protected void die() {
        this.isAlive = false;
    }

    protected void outBound() {
        if (this.x < 0 || this.y < 0 || this.x > TankFrame.GAME_WIDTH || this.y > TankFrame.GAME_HEIGHT) {
            die();
        }
    }


}
