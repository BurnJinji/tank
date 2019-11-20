package com.burning8393.tank;

import java.awt.*;

public class Explode extends GameObject {
    private int x, y;

    private boolean isAlive = true;

    private GameModel gm;

    private int step;

    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    public Explode(int x, int y, GameModel gm) {

        this.x = x;
        this.y = y;
        this.gm = gm;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }


    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            gm.objects.remove(this);
        }

    }
}
