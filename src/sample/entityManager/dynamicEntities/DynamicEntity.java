package sample.entityManager.dynamicEntities;

import sample.entityManager.Entity;
import sample.map.Tile;

public class DynamicEntity extends Entity {

    private String assetPath;
    private Direction buffer = Direction.RIGHT;

    public DynamicEntity(String assetPath) {
        this.assetPath = assetPath;
    }

    public Direction getBuffer() {
        return buffer;
    }

    public void setBuffer(Direction buffer) {
        this.buffer = buffer;
    }

    public String getAssetPath() {
        return assetPath;
    }
}
