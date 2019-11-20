package com.burning8393.tank;

import com.burning8393.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private static final GameModel INSTANCE = new GameModel();

    private Tank mainTank = new Tank(200, 400, Dir.UP, Group.GOOD, this);

    public List<GameObject> objects = new ArrayList<>();

    private ColliderChain colliderChain = new ColliderChain();

    private GameModel() {
        init();
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    private void init() {
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        int tanksPerRow = 10;
        for (int i = 0; i < initTankCount; i++) {
            objects.add(new Tank(50 + 80 * (i % tanksPerRow), 100 * ((i / tanksPerRow) + 1), Dir.DOWN, Group.BAD, this));
        }
    }

    public void paint(Graphics g) {
        mainTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                colliderChain.collide(objects.get(i), objects.get(j));
            }
        }
    }

    public Tank getMainTank() {
        return mainTank;
    }
}
