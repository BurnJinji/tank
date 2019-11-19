package com.burning8393.tank.abstractfactory;

import com.burning8393.tank.Audio;
import com.burning8393.tank.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode {
    private int x, y;

    private TankFrame tf;

    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {

        this.x = x;
        this.y = y ;
        this.tf = tf;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }


    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10 * step, 10 * step);
        step++;
        if (step >= 15) {
            tf.explodes.remove(this);
        }

        g.setColor(c);
    }
}
