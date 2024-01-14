package pl.umcs.map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pl.umcs.entities.Entity;

import java.util.ArrayList;

class MapTests {
    public static Entity testEntity = new Entity();
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
    private static Map map;

    @BeforeAll
    public static void setup() {
        map =
                Map.builder()
                        .level(new Field[11][18])
                        .rows(11)
                        .cols(18)
                        .entities(new ArrayList<>())
                        .items(new ArrayList<>())
                        .build();
        map.load(fields);
    }

    @Test
    public void isInBounds_InBoundsLower_ReturnsTrue() {
        boolean inBounds = map.isInBounds(0, 0);
        assertTrue(inBounds);
    }

    @Test
    public void isInBounds_InBoundsUpper_ReturnsTrue() {
        boolean inBounds = map.isInBounds(map.getRows() - 1, map.getCols() - 1);
        assertTrue(inBounds);
    }

    @Test
    public void isInBounds_OutOfBoundsColsLower_ReturnsFalse() {
        boolean inBounds = map.isInBounds(-1, 0);
        assertFalse(inBounds);
    }

    @Test
    public void isInBounds_OutOfBoundsColsUpper_ReturnsFalse() {
        boolean inBounds = map.isInBounds(map.getRows() + 1, 0);
        assertFalse(inBounds);
    }

    @Test
    public void isInBounds_OutOfBoundsRowsLower_ReturnsFalse() {
        boolean inBounds = map.isInBounds(0, -1);
        assertFalse(inBounds);
    }

    @Test
    public void isInBounds_OutOfBoundsRowsUpper_ReturnsFalse() {
        boolean inBounds = map.isInBounds(0, map.getCols() + 1);
        assertFalse(inBounds);
    }

    @Test
    public void canPlaceEntity_OutOfBoundsAndFieldTypeFloorAndNoEntityPresent_ReturnsFalse() {
        boolean canBePlaced = map.canPlaceEntity(-1, -1);
        assertFalse(canBePlaced);
    }

    @Test
    public void canPlaceEntity_InBoundsAndFieldTypeFloorAndNoEntityPresent_ReturnsTrue() {
        boolean canBePlaced = map.canPlaceEntity(1, 1);
        assertTrue(canBePlaced);
    }

    @Test
    public void canPlaceEntity_InBoundsAndFieldTypeBridgeAndNoEntityPresent_ReturnsTrue() {
        boolean canBePlaced = map.canPlaceEntity(6, 2);
        assertTrue(canBePlaced);
    }

    @Test
    public void canPlaceEntity_InBoundsAndFieldTypeFloorAndEntityPresent_ReturnsFalse() {
        map.placeEntity(1, 1, testEntity);
        boolean canBePlaced = map.canPlaceEntity(1, 1);
        map.removeEntity(1, 1, testEntity);
        assertFalse(canBePlaced);
    }

    @Test
    public void canPlaceEntity_InBoundsAndFieldTypeBridgeAndEntityPresent_ReturnsFalse() {
        map.placeEntity(6, 2, testEntity);
        boolean canBePlaced = map.canPlaceEntity(6, 2);
        map.removeEntity(6, 2, testEntity);
        assertFalse(canBePlaced);
    }

    @Test
    public void load() {
        for (int i = 0; i < map.getRows(); i++) {
            for (int j = 0; j < map.getCols(); j++) {
                System.out.print(map.getLevel()[i][j].getSymbol());
                Assertions.assertEquals(fields[i][j], map.getLevel()[i][j].getSymbol());
            }
            System.out.println();
        }
    }
}
