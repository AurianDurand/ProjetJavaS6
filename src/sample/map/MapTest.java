package sample.map;

import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    public void testIsTheTileAPath() throws Exception {

        Map map = new Map(10,10);
        Tile tile = new Path(0,0);
        assertEquals(false, map.isTheTileAPath(0,0));

    }

    @Test
    public void testIsTheTileAWallh() throws Exception {

        Map map = new Map(10,10);
        Tile tile = new Path(0,0);
        assertEquals(false, map.isTheTileAWall(0,0));

    }

}