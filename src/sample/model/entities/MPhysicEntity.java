package sample.model.entities;

import sample.parser.Tile;

public abstract class MPhysicEntity extends MBasicEntity {
    public MPhysicEntity(Tile tile) {
        super(tile);
    }

    public abstract boolean onCollide(MPhysicEntity entity);
}
