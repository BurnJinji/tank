package com.burning8393.tank.fire;

import com.burning8393.tank.Tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void fire(Tank tank);
}
