package sample.model.entities;

import sample.parser.Tile;

public class MEntityManager {
    public static MBasicEntity create(Tile tile) {

        if(tile.getType().equals("PACMAN")) {
            return new MPacman(tile);
        }
        else if(tile.getType().equals("WALL")) {
            return new MWall(tile);
        }
        else if(tile.getType().equals("PACGUM1")) {
            return new MPacGum(tile, 10);
        }
        else if(tile.getType().equals("PACGUM2")) {
            return new MPacGum(tile, 100);
        }
        else if(tile.getType().equals("PACGUM3")) {
            return new MPacGum(tile, 500);
        }
        else if(tile.getType().equals("GHOST")) {
//            return new MWall(tile);
        }

        return new MBasicEntity(tile);
    }
}
