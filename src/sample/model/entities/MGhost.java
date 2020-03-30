package sample.model.entities;

import sample.model.MMap;
import sample.parser.Tile;

import java.util.Random;

public class MGhost extends MDynamicEntity {
    public enum GHOST_STATE {
        VULNERABLE,
        INVULNARABLE
    }

    private long newDirectionTime;
    private long newDirectionClock;

    private GHOST_STATE state;

    public GHOST_STATE getState() { return this.state; }

    public MGhost(Tile tile, ENTITY_TYPE type) {
        super(tile, type);
        this.state =  GHOST_STATE.INVULNARABLE;
        this.newDirectionTime = 600;
        this.newDirectionClock = 0;

        this.addCollision(ENTITY_TYPE.WALL, false);
        this.addCollision(ENTITY_TYPE.GHOST, false);
        this.addCollision(ENTITY_TYPE.PACMAN, true);
    }

    @Override
    public void onUpdate(long deltaTime) {
        super.onUpdate(deltaTime);

        this.newDirectionClock += deltaTime;
        if (this.newDirectionClock > this.newDirectionTime) {
            this.newDirectionClock -= this.newDirectionTime;
            this.getNextDirection();
        }
    }

    @Override
    public void onMove() {
        super.onMove();
    }

    private void getNextDirection() {
        int randValue = new Random().nextInt(4);
        switch (randValue) {
            case 0:
                this.setDirection(MMap.DIRECTION.UP);
                break;
            case 1:
                this.setDirection(MMap.DIRECTION.DOWN);
                break;
            case 2:
                this.setDirection(MMap.DIRECTION.LEFT);
                break;
            case 3:
                this.setDirection(MMap.DIRECTION.RIGHT);
                break;
            default:
                break;
        }
    }

    @Override
    public void onSensorCollide(MPhysicEntity entity) {
        if(entity instanceof MPacman) {
            MPacman e = (MPacman) entity;
            if(this.getState() == GHOST_STATE.INVULNARABLE) {
                e.kill();
            }
        }
    }

    @Override
    public void onPhysicCollide(MPhysicEntity entity) {
        this.newDirectionClock = 0;
        this.getNextDirection();
    }
}
