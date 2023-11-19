package pl.umcs.map;

import lombok.*;

import org.jetbrains.annotations.NotNull;

import pl.umcs.Entity;
import pl.umcs.Item;

import java.util.ArrayList;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Map {
    // TODO: Remove or refactor when done better
    private Field[][] level;
    private int rows; // [rows][    ]
    private int cols; // [    ][cols]

    private ArrayList<Entity> entities;
    private ArrayList<Item> items;

    public boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x <= cols && y <= rows;
    }

    public boolean canPlaceItem(int x, int y) {
        return isInBounds(x, y)
                && (level[x][y] instanceof Floor
                        || level[x][y] instanceof Bridge)
                && level[x][y].items.isEmpty();
    }

    public boolean canPlaceEntity(int x, int y) {
        if (!isInBounds(x, y)) return false;
        if (level[x][y].entity != null) return false;
        return (level[x][y] instanceof Floor
                || level[x][y] instanceof Bridge);
    }

    public void placeItem(int x, int y, Item item) {
        if (!canPlaceItem(x, y)) return;

        level[x][y].items.add(item);
    }

    public void placeEntity(int x, int y, Entity entity) {
        if (!canPlaceEntity(x, y)) return;

        level[x][y].entity = entity;
        entities.add(entity);
    }

    public void removeItem(int x, int y, Item item) {
        level[x][y].items.remove(item);
    }

    public void removeEntity(int x, int y) {
        entities.remove(level[x][y].entity);
        level[x][y].entity = null;
    }

    public void removeEntity(int x, int y, Entity entity) {
        if (level[x][y].entity == entity) {
            entities.remove(level[x][y].entity);
            level[x][y].entity = null;
        }
    }

    public void load(@NotNull String mapData) {

    }

    public void changeFieldType(int x, int y, Field field) {
        level[x][y] = field;
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(level[i][j].getSymbol());
            }
            System.out.println();
        }
    }
}
