package sample.model.entities;

import sample.model.MMap;
import sample.parser.Tile;

public class MPacman extends MDynamicEntity {
    private int score;

    public int getScore() { return this.score; }

    public MPacman(Tile tile) {
        super(tile);
        this.score = 0;
    }

    public void setDirection(MMap.DIRECTION direction) {
        this.currentDirection = direction;
    }

    @Override
    public void onUpdate(long deltaTime) {

    }

    @Override
    public boolean onCollide(MPhysicEntity entity) {
        if(entity instanceof MWall) {
            return false;
        }
        else if(entity instanceof MPacGum) {
            MPacGum e = (MPacGum) entity;
            this.score += e.getPointValue();
            e.kill();
        }

        return true;
    }
}
