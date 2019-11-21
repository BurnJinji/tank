package com.burning8393.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    public static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth");
    public static final int GAME_HEIGHT = PropertyMgr.getInt("gameHeight");

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_WIDTH);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        GameModel.getInstance().paint(g);

    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    bU = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    setMainTankDir();
                    break;
                default:
                    break;
            }
            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_CONTROL:
//                    GameModel.getInstance().getMainTank().fire();
                    GameModel.getInstance().getMainTank().emitFireEvent();

                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            Tank mainTank = GameModel.getInstance().getMainTank();
            if (!bU && !bR && !bL && !bD) {
                mainTank.setMoving(false);
                return;
            } else {
                if (bL)  mainTank.setDir(Dir.LEFT);
                if (bR)  mainTank.setDir(Dir.RIGHT);
                if (bU)  mainTank.setDir(Dir.UP);
                if (bD)  mainTank.setDir(Dir.DOWN);
                mainTank.setMoving(true);
            }
        }

    }

}
