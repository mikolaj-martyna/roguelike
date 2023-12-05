package pl.umcs.map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

class MapTest {

    @org.junit.jupiter.api.Test
    void isInBounds() {}

    @org.junit.jupiter.api.Test
    void canPlaceItem() {}

    @org.junit.jupiter.api.Test
    void canPlaceEntity() {}

    @org.junit.jupiter.api.Test
    void placeItem() {}

    @org.junit.jupiter.api.Test
    void placeEntity() {}

    @org.junit.jupiter.api.Test
    void removeItem() {}

    @org.junit.jupiter.api.Test
    void removeEntity() {}

    @org.junit.jupiter.api.Test
    void testRemoveEntity() {}

    @org.junit.jupiter.api.Test
    void changeFieldType() {}

    @org.junit.jupiter.api.Test
    void load() {
        char[][] fields = {
            {
                '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '|', '.', '.', '.', '.', '|', '-', '-', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '-', '-', '#', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', '-', '-', '-', '-', '-', '-',
                '-'
            },
            {
                ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', '.', '.', '.', '.', '.',
                '|'
            },
            {
                ' ', ' ', '#', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#', '.', '.', '.', '.', '.',
                '|'
            },
            {
                ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', '|', '.', '.', '.', '.', '.',
                '|'
            },
            {
                ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', ' ', '-', '-', '-', '-', '-', '-',
                '-'
            }
        };
        Map map = Map.builder().level(new Field[11][19]).build();
        map.load(fields);

        for (int i = 0; i < map.getRows(); i++) {
            for (int j = 0; j < map.getCols(); j++) {
                System.out.print(map.getLevel()[i][j].getSymbol());
                Assertions.assertEquals(fields[i][j], map.getLevel()[i][j].getSymbol());
            }
            System.out.println();
        }
    }

    @org.junit.jupiter.api.Test
    void print() {}
}
