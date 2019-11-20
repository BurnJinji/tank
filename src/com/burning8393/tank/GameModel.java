package com.burning8393.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private static final GameModel INSTANCE = new GameModel();

    private Tank mainTank = new Tank(200, 400, Dir.UP, Group.GOOD, this);
    public List<Bullet> bullets = new ArrayList<>();
    public List<Tank> enemies = new ArrayList<>();
    public List<Explode> explodes = new ArrayList<>();

    private GameModel() {
        init();
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    private void init() {
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            enemies.add(new Tank(50 + 80 * i, 200, Dir.DOWN, Group.BAD, this));
        }
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        g.drawString("敌人的数量:" + enemies.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        g.setColor(c);
        mainTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).paint(g);
        }

        // collide detect
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                bullets.get(i).collideWith(enemies.get(j));
            }
        }
    }

    public Tank getMainTank() {
        return mainTank;
    }
}
