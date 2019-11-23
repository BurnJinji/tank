package com.burning8393.tank;

import com.burning8393.tank.cor.ColliderChain;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private static final GameModel INSTANCE = new GameModel();

    private Tank mainTank;

    private List<GameObject> objects = new ArrayList<>();

    private ColliderChain colliderChain = new ColliderChain();

    private GameModel() {
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    static {
        INSTANCE.init();
    }
    private void init() {
        mainTank = new Tank(350, 400, Dir.UP, Group.GOOD);
        mainTank.setMoving(false);
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        int tanksPerRow = 10;
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + 80 * (i % tanksPerRow), 120 * ((i / tanksPerRow) + 1), Dir.DOWN, Group.BAD);
        }

        new Wall(100, 80, 300, 50);
        new Wall(500, 80, 300, 50);
        new Wall(250, 350, 50, 300);
        new Wall(600, 350, 50, 300);

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

    public void add(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        objects.remove(gameObject);
    }

    public Tank getMainTank() {
        return mainTank;
    }

    public void save() {
        File f = new File("F:/mashibing/docs/.note/tank.data");
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(mainTank);
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        File f = new File("F:/mashibing/docs/.note/tank.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(f));
            mainTank = (Tank) ois.readObject();
            objects = (List<GameObject>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
