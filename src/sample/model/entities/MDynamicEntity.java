package sample.model.entities;

import sample.model.MMap;
import sample.parser.Tile;

public abstract class MDynamicEntity extends MPhysicEntity {
    MMap.DIRECTION currentDirection;

    public MMap.DIRECTION getCurrentDirection() { return this.currentDirection; }

    public MDynamicEntity(Tile tile) {
        super(tile);
        this.currentDirection = MMap.DIRECTION.IDLE;
    }

    public abstract void onUpdate(long deltaTime);
}
