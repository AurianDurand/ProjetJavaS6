package sample.model.entities;

import sample.parser.Tile;

public class MEntityManager {
    public static MBasicEntity create(Tile tile) {

        if(tile.getType().equals("PACMAN")) {
            return new MPacman(tile, MBasicEntity.ENTITY_TYPE.PACMAN);
        }
        else if(tile.getType().equals("WALL")) {
            return new MWall(tile, MBasicEntity.ENTITY_TYPE.WALL);
        }
        else if(tile.getType().equals("PACGUM1")) {
            return new MPacGum(tile, MBasicEntity.ENTITY_TYPE.PACGUM, 10);
        }
        else if(tile.getType().equals("PACGUM2")) {
            return new MPacGum(tile, MBasicEntity.ENTITY_TYPE.PACGUM, 100);
        }
        else if(tile.getType().equals("PACGUM3")) {
            return new MPacGum(tile, MBasicEntity.ENTITY_TYPE.PACGUM, 1000);
        }
        else if(tile.getType().equals("GHOST")) {
            return new MGhost(tile, MBasicEntity.ENTITY_TYPE.GHOST);
        }

        return new MBasicEntity(tile, MBasicEntity.ENTITY_TYPE.TILE);
    }
}
