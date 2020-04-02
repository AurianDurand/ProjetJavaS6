package sample.entityManager.dynamicEntities;

import sample.parser.Tile;

public class Ghost extends DynamicEntity {

    public Ghost(String assetPath) {
        super(assetPath);
    }

    public Ghost(Tile tile) {
        super(tile);
    }
}
