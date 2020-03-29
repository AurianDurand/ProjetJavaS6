package sample.entityManager.objects;

import sample.entityManager.Entity;
import sample.map.Tile;

public class Object implements Entity {

    private String assetPath;

    public Object(String assetPath) {
        this.assetPath = assetPath;
    }
}
