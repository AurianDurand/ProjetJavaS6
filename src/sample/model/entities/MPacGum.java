package sample.model.entities;

import sample.parser.Tile;

public class MPacGum extends MPhysicEntity {
    private int pointsValue;

    public int getPointValue() { return this.pointsValue; }

    public MPacGum(Tile tile, int pointsValue) {
        super(tile);
        this.pointsValue = pointsValue;
    }

    @Override
    public boolean onCollide(MPhysicEntity entity) {
        return false;
    }
}
