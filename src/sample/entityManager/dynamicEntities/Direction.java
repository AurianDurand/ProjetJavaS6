package sample.entityManager.dynamicEntities;

import java.util.Random;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    IDLE;

    public static Direction getRandomDirection() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
