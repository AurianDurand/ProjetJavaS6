package sample.model.entities;

import sample.parser.Tile;

public class MPacGum extends MPhysicEntity {
    private int pointsValue;

    public int getPointValue() { return this.pointsValue; }

    public MPacGum(Tile tile, ENTITY_TYPE type, int pointsValue) {
        super(tile, type);
        this.pointsValue = pointsValue;
    }

    @Override
    public void onSensorCollide(MPhysicEntity entity) {
    }

    @Override
    public void onPhysicCollide(MPhysicEntity entity) {

    }
}
