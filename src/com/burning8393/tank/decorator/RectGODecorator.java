package com.burning8393.tank.decorator;

import com.burning8393.tank.GameModel;
import com.burning8393.tank.GameObject;

import java.awt.*;

public class RectGODecorator extends GODecorator {

    public RectGODecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        if (!isAlive) {
            GameModel.getInstance().remove(this);
        }
        this.x = super.go.getX();
        this.y = super.go.getY();
        super.go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(x, y, super.go.getWidth() + 2, super.go.getHeight() + 2);
        g.setColor(c);
        outBound();
    }

    @Override
    public int getX() {
        return super.go.getX();
    }

    @Override
    public int getY() {
        return super.go.getY();
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }


}
