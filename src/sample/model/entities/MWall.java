package sample.model.entities;

import sample.parser.Tile;

public class MWall extends MPhysicEntity {
    public MWall(Tile tile, ENTITY_TYPE type) {
        super(tile, type);
    }

    @Override
    public void onSensorCollide(MPhysicEntity entity) {
    }

    @Override
    public void onPhysicCollide(MPhysicEntity entity) {

    }
}
