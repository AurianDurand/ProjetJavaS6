package sample.model.entities;

import sample.parser.Tile;

public class MWall extends MPhysicEntity {
    public MWall(Tile tile) {
        super(tile);
    }

    @Override
    public boolean onCollide(MPhysicEntity entity) {
        return true;
    }
}
