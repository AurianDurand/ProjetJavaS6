package sample.model.entities;

import javafx.util.Pair;
import sample.parser.Tile;
import java.util.ArrayList;

public abstract class MPhysicEntity extends MBasicEntity {
    private ArrayList<ENTITY_TYPE> physicCollisionList;
    private ArrayList<ENTITY_TYPE> sensorCollisionList;

    public MPhysicEntity(Tile tile, ENTITY_TYPE type) {
        super(tile, type);
        this.physicCollisionList = new ArrayList<>();
        this.sensorCollisionList = new ArrayList<>();
    }

    public void addCollision(ENTITY_TYPE type, boolean isSensor) {
        if(isSensor){
            this.sensorCollisionList.add(type);
        }
        else {
            this.physicCollisionList.add(type);
        }
    }

    public boolean containCollision(ENTITY_TYPE type, boolean isSensor) {
        if(isSensor) {
            return this.sensorCollisionList.contains(type);
        }
        else {
            return this.physicCollisionList.contains(type);
        }
    }

    public abstract void onSensorCollide(MPhysicEntity entity);
    public abstract void onPhysicCollide(MPhysicEntity entity);
}
