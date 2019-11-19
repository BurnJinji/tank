package com.burning8393.tank;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            tf.enemies.add(tf.gf.createTank(50 + 80 * i, 200, Dir.DOWN, Group.BAD, tf));
        }

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true) {
            TimeUnit.MILLISECONDS.sleep(50);
            tf.repaint();
        }
    }
}
