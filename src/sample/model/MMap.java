package sample.model;

import javafx.util.Pair;
import sample.model.entities.*;
import sample.parser.Layers;
import sample.parser.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MMap {
    public enum DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        IDLE
    }

    private Point size;
    private Point tilesize;
    private ArrayList<HashMap<Point, MBasicEntity>> layers;
    private ArrayList<MPacman> players;
    private Layers rawlayers;

    public Point getSize() { return this.size; }

    public Point getTilesize() { return this.tilesize; }

    public int getNbLayer() { return this.layers.size(); }

    public int getScore(int idPlayer) {
        if(this.players.size() > 0 && idPlayer < this.players.size()) {
            return this.players.get(idPlayer).getScore();
        }

        return 0;
    }

    public MMap() {
        this.layers = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public void update(long deltaTime) {
        this.updateInfoMap();
        this.deleteDeadEntities();
        this.updateEntities(deltaTime);
    }

    private void updateInfoMap() {
        if(this.getEntitiesByType(MBasicEntity.ENTITY_TYPE.PACMAN).size() <= 0) {
            if(this.rawlayers != null) {
                this.create(this.rawlayers);
            }
        }
    }

    private void deleteDeadEntities() {
        for(HashMap<Point, MBasicEntity> hashMap : this.layers) {
            ArrayList<Point> positions = new ArrayList<>();
            for (HashMap.Entry<Point, MBasicEntity> entry : hashMap.entrySet()) {
                if (!entry.getValue().getIsAlive()) {
                    positions.add(entry.getKey());
                }
            }
            for(Point position: positions) {
                hashMap.remove(position);
            }
        }
    }

    private void updateEntities(long deltaTime) {
        for(HashMap<Point, MBasicEntity> layer : this.layers) {
            List<Point> keyList = new ArrayList<>(layer.keySet());
            for(int i = 0; i < keyList.size();) {
                Point key = keyList.get(i);
                MBasicEntity entity = layer.get(key);

                if(entity instanceof MDynamicEntity) {
                    MDynamicEntity dynamicEntity = (MDynamicEntity) entity;
                    dynamicEntity.onUpdate(deltaTime);

                    DIRECTION direction = dynamicEntity.getCurrentDirection();
                    if(direction != DIRECTION.IDLE && dynamicEntity.getCanMove()) {
                        Point p = new Point(0, 0);
                        if(direction == DIRECTION.UP) {
                            p.y -= 1;
                        }
                        else if(direction == DIRECTION.DOWN) {
                            p.y += 1;
                        }
                        else if(direction == DIRECTION.LEFT) {
                            p.x -= 1;
                        }
                        else if(direction == DIRECTION.RIGHT) {
                            p.x += 1;
                        }
                        Point currentPosition = key;
                        Point nextPosition = new Point(key.x + p.x,key.y + p.y);

                        if(this.physicCollideEnvent(dynamicEntity, nextPosition)) {
                            this.sensorCollideEvent(dynamicEntity, nextPosition);
                            if(dynamicEntity.getIsAlive()){
                                dynamicEntity.onMove();
                                layer.put(nextPosition, dynamicEntity);
                            }

                            layer.remove(currentPosition);
                        }
                        else i++;
                    }
                    else i++;
                }
                else i++;
            }
        }
    }

    private boolean physicCollideEnvent(MDynamicEntity entityToMove, Point pointTargeted) {
        ArrayList<MBasicEntity> entities = this.getEntitiesByPosition(pointTargeted);
        for(MBasicEntity entity : entities) {
            if(entityToMove.containCollision(entity.getType(), false)) {
                MPhysicEntity physicEntity = (MPhysicEntity) entity;
                entityToMove.onPhysicCollide(physicEntity);
                return false;
            }
        }

        return true;
    }

    private void sensorCollideEvent(MDynamicEntity entityMoved, Point newPosition) {
        ArrayList<MBasicEntity> entities = this.getEntitiesByPosition(newPosition);
        for(MBasicEntity entity : entities) {
            if(entityMoved.containCollision(entity.getType(), true)) {
                MPhysicEntity physicEntity  = (MPhysicEntity) entity;
                entityMoved.onSensorCollide(physicEntity);
            }
        }
    }

    public void setPlayerDirection(DIRECTION input) {
        ArrayList<MBasicEntity> entities = this.getEntitiesByType(MBasicEntity.ENTITY_TYPE.PACMAN);

        for(MBasicEntity entity : entities) {
            if(entity instanceof MDynamicEntity) {
                MDynamicEntity p = (MDynamicEntity) entity;
                p.setDirection(input);
            }
        }
    }

    public MBasicEntity getEntityByPosition(Point point, int idLayer) {
        if(idLayer < this.layers.size()) {
            return this.layers.get(idLayer).get(point);
        }
        return null;
    }

    public ArrayList<MBasicEntity> getEntitiesByType(MBasicEntity.ENTITY_TYPE type) {
        ArrayList<MBasicEntity> entities = new ArrayList<>();

        for(HashMap<Point, MBasicEntity> hashMap : this.layers) {
            for (HashMap.Entry<Point, MBasicEntity> entry : hashMap.entrySet()) {
                if (entry.getValue().getType().equals(type)) {
                    entities.add(entry.getValue());
                }
            }
        }

        return entities;
    }

    public ArrayList<MBasicEntity> getEntitiesByPosition(Point point) {
        ArrayList<MBasicEntity> e = new ArrayList<>();

        for(HashMap<Point, MBasicEntity> hashMap : this.layers) {
            if(hashMap.containsKey(point)) {
                e.add(hashMap.get(point));
            }
        }
        return e;
    }

    public boolean create(Layers data) {
        //On test si le parser n'a pas d'erreur
        if(data != null) {
            List<Tile[][]> layers = data.getLayers();
            if(layers != null) {
                this.size = new Point(data.getWidth(), data.getHeight());
                this.tilesize = new Point(data.getTilewidth(), data.getTileheight());
                this.layers.clear();
                this.rawlayers = data;
                //On parcourt chaque layer
                for(int k = 0; k < layers.size(); k++) {
                    Tile[][] layer = layers.get(k);
                    this.layers.add(new HashMap<Point, MBasicEntity>());
                    //On parcourt chaque case
                    for (int i = 0; i < layer.length; i++) {
                        for (int j = 0; j < layer[i].length; j++) {
                            //si la case est pleine
                            if (layer[i][j] != null) {
                                //Si il n'y en a pas, on n'en créer une. Sinon on ajoute une autre entité
                                this.addEntity(k, new Point(j, i), MEntityManager.create(layer[i][j]));
                            }
                        }
                    }
                }

                this.initPlayers();
                return true;
            }
        }
        return false;
    }

    private void initPlayers() {
        this.players.clear();

        ArrayList<MBasicEntity> entities = this.getEntitiesByType(MBasicEntity.ENTITY_TYPE.PACMAN);
        for(MBasicEntity e : entities) {
            if(e instanceof MPacman) {
                MPacman player = (MPacman) e;
                this.players.add(player);
            }
        }
    }

    private void addEntity(int idLayer, Point pos, MBasicEntity entity) {
        if(idLayer < this.layers.size()) {
            this.layers.get(idLayer).put(pos, entity);
        }
    }
}
