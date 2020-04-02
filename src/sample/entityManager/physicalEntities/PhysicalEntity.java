package sample.entityManager.physicalEntities;

import sample.entityManager.Entity;
import sample.parser.Tile;

public class PhysicalEntity extends Entity {

    public PhysicalEntity(String assetPath) {
        super(assetPath);
    }

    public PhysicalEntity(Tile tile) {
        super(tile);
    }
}
