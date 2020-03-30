package sample.model.entities;

import sample.model.MMap;
import sample.parser.Tile;

public abstract class MDynamicEntity extends MPhysicEntity {
    protected MMap.DIRECTION currentDirection;
    private boolean canMove;
    private long movingClock;
    protected long timeToMove;

    public MMap.DIRECTION getCurrentDirection() { return this.currentDirection; }

    public boolean getCanMove() { return this.canMove; }

    public void setDirection(MMap.DIRECTION direction) {
        this.currentDirection = direction;
    }

    public MDynamicEntity(Tile tile, ENTITY_TYPE type) {
        super(tile, type);
        this.currentDirection = MMap.DIRECTION.IDLE;
        this.canMove = false;
        this.movingClock = 0;
        this.timeToMove = 300;
    }

    public void onUpdate(long deltaTime) {
        this.movingClock += deltaTime;
        if(this.movingClock > this.timeToMove) {
            this.movingClock -= this.timeToMove;
            this.canMove = true;
        }
    }

    public void onMove() {
        this.canMove = false;
    }
}
