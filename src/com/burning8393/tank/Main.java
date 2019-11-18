package com.burning8393.tank;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        for (int i = 0; i < 5; i++) {
            tf.enemies.add(new Tank(50 + 80 * i, 200, Dir.DOWN, tf));
        }
        while (true) {
            TimeUnit.MILLISECONDS.sleep(50);
            tf.repaint();
        }
    }
}
