package sample.entityManager.dynamicEntities;

import sample.map.Tile;

public class Pacman extends DynamicEntity {

    private int lifes = 3;

    public Pacman(Tile tile) {
        super(tile);
    }
}
