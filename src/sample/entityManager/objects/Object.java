package sample.entityManager.objects;

import sample.entityManager.Entity;
import sample.parser.Tile;

public class Object extends Entity {

    public Object(String assetPath) {
        super(assetPath);
    }

    public Object(Tile tile) {
        super(tile);
    }
}
