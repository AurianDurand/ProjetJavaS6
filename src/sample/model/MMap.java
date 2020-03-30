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

    public Point getSize() { return this.size; }

    public Point getTilesize() { return this.tilesize; }

    public int getNbLayer() { return this.layers.size(); }

    public int getScore(int idPlayer) {
        ArrayList<MBasicEntity> entities = this.getEntitiesByType("PACMAN");
        if(entities.size() > 0 && idPlayer < entities.size()) {
            if(entities.get(idPlayer) instanceof MPacman) {
                MPacman player = (MPacman) entities.get(idPlayer);
                return player.getScore();
            }
        }

        return 0;
    }

    public MMap() {
        this.layers = new ArrayList<>();
    }

    private long clockMove = 0;

    public void update(long deltaTime) {
        clockMove += deltaTime;
        if(clockMove < 200) {
            return;
        }
        clockMove -= 200;

        this.deleteDeadEntities();
        this.updateEntities(deltaTime);
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
        ArrayList<Pair<Pair<Point, Point>, MBasicEntity>> entitiesToMove = new ArrayList<>();

        for(HashMap<Point, MBasicEntity> layer : this.layers) {
            for (HashMap.Entry<Point, MBasicEntity> entry : layer.entrySet()) {
                if (entry.getValue() instanceof MDynamicEntity) {
                    MDynamicEntity entity = (MDynamicEntity) entry.getValue();
                    entity.onUpdate(deltaTime);

                    DIRECTION key = entity.getCurrentDirection();
                    if(key != DIRECTION.IDLE) {
                        Point p = new Point(0, 0);
                        if(key == DIRECTION.UP) {
                            p.y -= 1;
                            entity.getTile().setRotation(0.0);
                        }
                        else if(key == DIRECTION.DOWN) {
                            p.y += 1;
                            entity.getTile().setRotation(180.0);
                        }
                        else if(key == DIRECTION.LEFT) {
                            p.x -= 1;
                            entity.getTile().setRotation(270.0);
                        }
                        else if(key == DIRECTION.RIGHT) {
                            p.x += 1;
                            entity.getTile().setRotation(90.0);
                        }
                        Point p1 = entry.getKey();
                        Point p2 = new Point(entry.getKey().x + p.x,entry.getKey().y + p.y);

                        if(this.canMoveTo(entity, p2)) {
                            entitiesToMove.add(new Pair<>(new Pair<>(p1, p2), entity));
                        }
                    }
                }
            }

            for(Pair<Pair<Point, Point>, MBasicEntity> e : entitiesToMove) {
                layer.put(e.getKey().getValue(), e.getValue());
                layer.remove(e.getKey().getKey());
            }
        }
    }

    private boolean canMoveTo(MDynamicEntity entityToMove, Point pointTargeted) {

        ArrayList<MBasicEntity> entities = this.getEntitiesByPosition(pointTargeted);

        for(MBasicEntity entity : entities) {
            if(entity instanceof MPhysicEntity) {
                MPhysicEntity pOther = (MPhysicEntity) entity;

                return entityToMove.onCollide(pOther);
            }
        }

        return true;
    }

    public void setPlayerDirection(DIRECTION input) {
        ArrayList<MBasicEntity> entities = this.getEntitiesByType("PACMAN");

        for(MBasicEntity entity : entities) {
            if(entity instanceof MPacman) {
                MPacman p = (MPacman) entity;
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

    public ArrayList<MBasicEntity> getEntitiesByType(String type) {
        ArrayList<MBasicEntity> entities = new ArrayList<>();

        for(HashMap<Point, MBasicEntity> hashMap : this.layers) {
            for (HashMap.Entry<Point, MBasicEntity> entry : hashMap.entrySet()) {
                if (entry.getValue().getTile().getType().equals(type)) {
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
                return true;
            }
        }
        return false;
    }

    private void addEntity(int idLayer, Point pos, MBasicEntity entity) {
        if(idLayer < this.layers.size()) {
            this.layers.get(idLayer).put(pos, entity);
        }
    }
}
