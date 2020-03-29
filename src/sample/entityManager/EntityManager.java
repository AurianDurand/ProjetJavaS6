package sample.entityManager;

import sample.entityManager.dynamicEntities.DynamicEntity;
import sample.entityManager.dynamicEntities.Ghost;
import sample.entityManager.dynamicEntities.Pacman;
import sample.entityManager.objects.PacGum;
import sample.entityManager.objects.SuperPacGum;
import sample.entityManager.physicalEntities.PhysicalEntity;
import sample.entityManager.physicalEntities.Wall;
import sample.map.Map;
import sample.map.Tile;

import java.util.ArrayList;

public class EntityManager {

    private Map map;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> entitiesToDelete = new ArrayList<>();

    public EntityManager(Map map) {
        this.map = map;
    }

    public Entity createEntity(EntityType entityType, int x, int y, String assetPath) {

        // first, create the entity
        Entity createdEntity = null;
        switch (entityType) {
            case PACMAN:
                createdEntity = new Pacman(assetPath);
                break;
            case GHOST:
                createdEntity = new Ghost(assetPath);
                break;
            case PACGUM:
                createdEntity = new PacGum(assetPath);
                break;
            case SUPERPACGUM:
                createdEntity = new SuperPacGum(assetPath);
                break;
            case WALL:
                createdEntity = new Wall(assetPath);
                break;
        }

        // second, save the entity into the entitiesList
        entities.add(createdEntity);

        // third, return the created entity
        return createdEntity;
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public void destroyEntity(Entity entity) {
        this.entities.remove(entity);
    }

    /**
     *
     * @return true if the move is possible and false if not
     */
    public boolean onCollide(Entity entityToMove, ArrayList<Entity> entitiesOnTargetTile) {

        // for each entity on the target tile
        for (Entity entityOnTargetTile : entitiesOnTargetTile) {

            // if the entity is a wall, return false
            if(entityOnTargetTile instanceof PhysicalEntity) {
                return false; // return false and stop the method
            }

            // if the entity is an object, pick it up
            else if (entityOnTargetTile instanceof Object) {

                // an object can be picked up only by Pac-Man
                if (entityToMove instanceof Pacman) {

                    // add the entity to the destroy list to destroy the object instance
                    entitiesToDelete.add(entityOnTargetTile);

                    // increment the points obtained by the player
                    ((Pacman) entityToMove).gainOnePoint();
                }
            }

            // if the entity is another dynamic entity
            else if (entityOnTargetTile instanceof DynamicEntity) {

                // only Pac-Man is affected on collide
                if (entityToMove instanceof Pacman) {

                    // if it is a ghost
                    if (entityOnTargetTile instanceof Ghost) {

                        // Pac-Man lose one life
                        ((Pacman) entityToMove).loseOneLife();

                        if(((Pacman) entityToMove).getLifes() == 0) {

                            // GAME OVER
                            this.entitiesToDelete.add(entityToMove); // delete Pac-Man because he has no life left
                        }
                    }
                }
            }
        }

        return true;
    }

    public ArrayList<Entity> getEntitiesToDelete() {
        return entitiesToDelete;
    }
}
