package sample.entityManager.physicalEntities;

import sample.parser.Tile;

public class Wall extends PhysicalEntity {

    public Wall(String assetPath) {
        super(assetPath);
    }

    public Wall(Tile tile) {
        super(tile);
    }
}
