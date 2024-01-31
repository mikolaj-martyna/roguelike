package pl.umcs.map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Player;
import pl.umcs.entities.monsters.Myr;
import pl.umcs.map.walls.HorizontalWall;
import pl.umcs.map.walls.VerticalWall;

class MapTests {
    private static final char[][] fields = {
        {'╔', '═', '═', '═', '═', '═', '═', '═', '═', '═', '╗', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'║', '░', '░', '░', '░', '░', '░', '░', '░', '░', '║', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'║', '░', '░', '░', '░', '░', '░', '░', '░', '░', '║', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'║', '░', '░', '░', '░', '░', '░', '░', '░', '░', '║', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'║', '░', '░', '░', '░', '░', '░', '░', '░', '░', '║', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'║', '░', '░', '░', '░', '╔', '═', '═', '═', '═', '╝', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'╚', '═', '#', '═', '═', '╝', ' ', ' ', ' ', ' ', ' ', '╔', '═', '═', '═', '═', '═', '╗'},
        {' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '║', '░', '░', '░', '░', '░', '║'},
        {' ', ' ', '#', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#', '░', '░', '░', '░', '░', '║'},
        {' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', '║', '░', '░', '░', '░', '░', '║'},
        {' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', ' ', '╚', '═', '═', '═', '═', '═', '╝'}
    };
    public static Entity testEntity = new Entity();
    private static Map loadedMap;

    // TODO: fix tests to work correctly after adding map generation :^)
    @Test
    public void isInBounds_InBoundsLower_ReturnsTrue() {
        boolean inBounds = loadedMap.isInBounds(0, 0);
        assertTrue(inBounds);
    }

    @Test
    public void isInBounds_InBoundsUpper_ReturnsTrue() {
        boolean inBounds =
                loadedMap.isInBounds(loadedMap.getWidth() - 1, loadedMap.getHeight() - 1);
        assertTrue(inBounds);
    }

    @Test
    public void isInBounds_OutOfBoundsColsLower_ReturnsFalse() {
        boolean inBounds = loadedMap.isInBounds(-1, 0);
        assertFalse(inBounds);
    }

    @Test
    public void isInBounds_OutOfBoundsColsUpper_ReturnsFalse() {
        boolean inBounds = loadedMap.isInBounds(loadedMap.getWidth() + 1, 0);
        assertFalse(inBounds);
    }

    @Test
    public void isInBounds_OutOfBoundsRowsLower_ReturnsFalse() {
        boolean inBounds = loadedMap.isInBounds(0, -1);
        assertFalse(inBounds);
    }

    @Test
    public void isInBounds_OutOfBoundsRowsUpper_ReturnsFalse() {
        boolean inBounds = loadedMap.isInBounds(0, loadedMap.getHeight() + 1);
        assertFalse(inBounds);
    }

    @Test
    public void canPlaceEntity_OutOfBoundsAndFieldTypeFloorAndNoEntityPresent_ReturnsFalse() {
        boolean canBePlaced = loadedMap.canPlaceEntity(-1, -1);
        assertFalse(canBePlaced);
    }

    @Test
    public void canPlaceEntity_InBoundsAndFieldTypeFloorAndNoEntityPresent_ReturnsTrue() {
        boolean canBePlaced = loadedMap.canPlaceEntity(1, 1);
        assertTrue(canBePlaced);
    }

    @Test
    public void canPlaceEntity_InBoundsAndFieldTypeBridgeAndNoEntityPresent_ReturnsTrue() {
        boolean canBePlaced = loadedMap.canPlaceEntity(6, 2);
        assertTrue(canBePlaced);
    }

    @Test
    public void canPlaceEntity_InBoundsAndFieldTypeFloorAndEntityPresent_ReturnsFalse() {
        loadedMap.placeEntity(1, 1, testEntity);
        boolean canBePlaced = loadedMap.canPlaceEntity(1, 1);
        loadedMap.removeEntity(1, 1, testEntity);
        assertFalse(canBePlaced);
    }

    @Test
    public void canPlaceEntity_InBoundsAndFieldTypeBridgeAndEntityPresent_ReturnsFalse() {
        loadedMap.placeEntity(6, 2, testEntity);
        boolean canBePlaced = loadedMap.canPlaceEntity(6, 2);
        loadedMap.removeEntity(6, 2, testEntity);
        assertFalse(canBePlaced);
    }

    @Test
    public void load_CheckOutputIfLoadedCorrectly_ReturnsTrue() {
        for (int i = 0; i < loadedMap.getWidth(); i++) {
            for (int j = 0; j < loadedMap.getHeight(); j++) {
                System.out.print(loadedMap.getFields()[i][j].getSymbol());
                Assertions.assertEquals(fields[i][j], loadedMap.getFields()[i][j].getSymbol());
            }
            System.out.println();
        }
    }

    @Test
    public void generateLevel_CheckOutput_ReturnsTrue() {
        Map map = new Map();

        map.print();
    }

    @Test
    public void pathToPlayer_PathFound_ReturnsLength() {
        Map map = new Map(1);
        Field[][] fields = {
            {
                new HorizontalWall(),
                new VerticalWall(),
                new VerticalWall(),
                new VerticalWall(),
                new HorizontalWall()
            },
            {new HorizontalWall(), new Floor(), new Floor(), new Floor(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new Floor(), new Floor(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new Floor(), new Floor(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new Floor(), new Floor(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new Floor(), new Floor(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new VerticalWall(), new VerticalWall(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new HorizontalWall(), new Floor(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new HorizontalWall(), new Floor(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new HorizontalWall(), new Floor(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new HorizontalWall(), new Floor(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new HorizontalWall(), new Floor(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new HorizontalWall(), new Floor(), new HorizontalWall()},
            {new HorizontalWall(), new Floor(), new Floor(), new Floor(), new HorizontalWall()},
            {
                new HorizontalWall(),
                new VerticalWall(),
                new VerticalWall(),
                new VerticalWall(),
                new HorizontalWall()
            }
        };

        Level level = new Level(fields, 15, 5);

        map.getLevels().clear();
        map.getLevels().add(level);

        Player player = new Player();
        Myr myr = new Myr();

        map.placeEntity(1, 1, player);
        map.placeEntity(8, 3, myr);

        int length = map.pathToPlayer(myr, player);

        map.print(level);
        System.out.println("Distance from entity to player: " + length);

        Assertions.assertEquals(19, length);
    }
}
