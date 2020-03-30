package sample.model.entities;

import sample.model.MGame;
import sample.model.MMap;
import sample.parser.Tile;

public class MPacman extends MDynamicEntity {
    private int score;

    public int getScore() { return this.score; }

    public MPacman(Tile tile, ENTITY_TYPE type) {
        super(tile, type);
        this.score = 0;
        this.timeToMove = 200;

        this.addCollision(ENTITY_TYPE.WALL, false);
        this.addCollision(ENTITY_TYPE.PACMAN, false);
        this.addCollision(ENTITY_TYPE.GHOST, true);
        this.addCollision(ENTITY_TYPE.PACGUM, true);
    }

    @Override
    public void onMove() {
        super.onMove();
        if(this.currentDirection == MMap.DIRECTION.UP) {
            this.getTile().setRotation(0.0);
        }
        else if(this.currentDirection == MMap.DIRECTION.DOWN) {
            this.getTile().setRotation(180.0);
        }
        else if(this.currentDirection == MMap.DIRECTION.LEFT) {
            this.getTile().setRotation(270.0);
        }
        else if(this.currentDirection == MMap.DIRECTION.RIGHT) {
            this.getTile().setRotation(90.0);
        }
    }

    @Override
    public void onSensorCollide(MPhysicEntity entity) {
        if(entity instanceof MPacGum) {
            MPacGum e = (MPacGum) entity;
            this.score += e.getPointValue();
            e.kill();
        }
        else if(entity instanceof MGhost) {
            MGhost e = (MGhost) entity;
            if(e.getState() == MGhost.GHOST_STATE.INVULNARABLE) {
                this.kill();
            }
        }
    }

    @Override
    public void onPhysicCollide(MPhysicEntity entity) {

    }
}
