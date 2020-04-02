package sample.entityManager.dynamicEntities;

import sample.entityManager.Entity;
import sample.map.Tile;

public class DynamicEntity extends Entity {

    private Direction buffer = Direction.IDLE;

    public DynamicEntity(String assetPath) {
        super(assetPath);
    }

    public Direction getBuffer() {
        return buffer;
    }

    public void setBuffer(Direction buffer) {
        this.buffer = buffer;
    }

}
