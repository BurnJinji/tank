package com.burning8393.tank;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true) {
            TimeUnit.MILLISECONDS.sleep(50);
            tf.repaint();
        }
    }
}
