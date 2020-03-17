package sample.map;

import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    public void testIsTheTileAPath() throws Exception {

        Map map = new Map(10,10);
        map.addPath(0,0);
        map.addWall(1,1);
        assertEquals(true, map.isTheTileAPath(0,0));
        assertEquals(false, map.isTheTileAPath(1,1));

    }

    @Test
    public void testIsTheTileAWallh() throws Exception {

        Map map = new Map(10,10);
        map.addWall(0,0);
        map.addPath(1,1);
        assertEquals(true, map.isTheTileAWall(0,0));
        assertEquals(false, map.isTheTileAWall(1,1));

    }

}