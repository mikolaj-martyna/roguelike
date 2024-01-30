package pl.umcs.map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pl.umcs.entities.Entity;

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

    @BeforeAll
    public static void setup() {
        loadedMap = new Map(11, 18);
        //        loadedMap.load(fields);
    }

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
}
