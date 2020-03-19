package sample.entityManager.dynamicEntities;

import sample.map.Tile;

public class Pacman extends DynamicEntity {

    private int lifes = 3;

    public Pacman(int x, int y, String assetPath) {
        super(x, y, assetPath);
    }
}
