package sample.entityManager;

import sample.entityManager.dynamicEntities.Direction;
import sample.entityManager.dynamicEntities.DynamicEntity;
import sample.entityManager.dynamicEntities.Ghost;
import sample.entityManager.dynamicEntities.Pacman;
import sample.entityManager.objects.Object;
import sample.entityManager.objects.PacGum;
import sample.entityManager.objects.SuperPacGum;
import sample.entityManager.physicalEntities.PhysicalEntity;
import sample.entityManager.physicalEntities.Wall;
import sample.map.Map;
//import sample.map.Tile;
import sample.parser.Tile;

import java.util.ArrayList;

public class EntityManager {

    private Map map;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> entitiesToDelete = new ArrayList<>();

    public EntityManager(Map map) {
        this.map = map;
    }

    public Entity createEntity(EntityType entityType, /*String assetPath,*/ Tile tile) {

        // first, create the entity
        Entity createdEntity = null;
        switch (entityType) {
            case PACMAN:
                createdEntity = new Pacman(tile);
                break;
            case GHOST:
                createdEntity = new Ghost(tile);
                break;
            case PACGUM:
                createdEntity = new PacGum(tile);
                break;
            case SUPERPACGUM:
                createdEntity = new SuperPacGum(tile);
                break;
            case WALL:
                createdEntity = new Wall(tile);
                break;
            case OTHER:
                createdEntity = new Entity(tile);
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

                // only for test
                if(entityOnTargetTile instanceof Ghost) {
                    ((DynamicEntity)entityToMove).setBuffer(Direction.getRandomDirection());
                }

                return false; // return false and stop the method
            }

            // if the entity is another dynamic entity
            else if (entityOnTargetTile instanceof DynamicEntity) {

                // only Pac-Man is affected on collide
                if (entityToMove instanceof Pacman) {

                    // if it is a ghost
                    if (entityOnTargetTile instanceof Ghost) {

                        // Pac-Man lose one life
                        ((Pacman) entityToMove).loseOneLife();

//                        System.out.println("  has collided with a ghost -> - 1 life");
//                        System.out.println("  remaining life: "+((Pacman) entityToMove).getLifes());

                        if(((Pacman) entityToMove).getLifes() == 0) {

                            // GAME OVER
                            this.entitiesToDelete.add(entityToMove); // delete Pac-Man because he has no life left
                        }
                    }
                }
            }

            // if the entity is an object, pick it up
            else if (entityOnTargetTile instanceof Object) {

                // an object can be picked up only by Pac-Man
                if (entityToMove instanceof Pacman) {

                    // add the entity to the destroy list to destroy the object instance
                    entitiesToDelete.add(entityOnTargetTile);

//                    System.out.println("  object picked up -> + 1p");

                    // increment the points obtained by the player
                    ((Pacman) entityToMove).gainOnePoint();

//                    System.out.println("  points: "+((Pacman) entityToMove).getPoints());
                }
            }
        }

        return true;
    }

    public ArrayList<Entity> getEntitiesToDelete() {
        return entitiesToDelete;
    }

    public void clearEntitiesToDelete() {
        this.entitiesToDelete.clear();
    }

    public ArrayList<Pacman> getPacman() {
        ArrayList<Pacman> toReturn = new ArrayList<>();
        for (Entity e: this.entities) {
            if (e instanceof Pacman) {
                toReturn.add((Pacman) e);
            }
        }
        return toReturn;
    }
}
