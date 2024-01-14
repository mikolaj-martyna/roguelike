package pl.umcs.map;

import lombok.*;

import pl.umcs.items.Item;
import pl.umcs.entities.Entity;
import pl.umcs.entities.Player;

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
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }

    public boolean canPlaceItem(int x, int y) {
        return isInBounds(x, y)
                && (level[x][y] instanceof Floor || level[x][y] instanceof Bridge)
                && level[x][y].items.isEmpty();
    }

    public boolean canPlaceEntity(int x, int y) {
        if (!isInBounds(x, y)) return false;
        if (level[x][y].entity != null) return false;
        return (level[x][y] instanceof Floor || level[x][y] instanceof Bridge);
    }

    public void placeItem(int x, int y, Item item) {
        if (!canPlaceItem(x, y)) return;

        level[x][y].items.add(item);
        items.add(item);
    }

    public void placeEntity(int x, int y, Entity entity) {
        if (!canPlaceEntity(x, y)) return;

        level[x][y].entity = entity;
        entities.add(entity);

        entity.setX(x);
        entity.setY(y);
    }

    public void removeItem(int x, int y, Item item) {
        level[x][y].items.remove(item);
        items.remove(item);
    }

    public void removeEntity(int x, int y) {
        entities.remove(level[x][y].entity);

        level[x][y].entity.setX(-1);
        level[x][y].entity.setY(-1);

        level[x][y].entity = null;
    }

    public void removeEntity(int x, int y, Entity entity) {
        if (level[x][y].entity == entity) {
            entities.remove(level[x][y].entity);
            level[x][y].entity = null;

            entity.setX(-1);
            entity.setY(-1);
        }
    }

    public void changeFieldType(int x, int y, Field field) {
        level[x][y] = field;
    }

    public void load(char[][] mapData) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Field field;
                char c = mapData[i][j];

                if (c == '.') {
                    field = new Floor();
                } else if (c == '-') {
                    field = new HorizontalWall();
                } else if (c == '|') {
                    field = new VerticalWall();
                } else if (c == '#') {
                    field = new Bridge();
                } else {
                    field = new Field();
                }

                changeFieldType(i, j, field);
            }
        }
    }

    public void print() {
        System.out.flush();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Entity entity = level[i][j].entity;

                if (entity == null && level[i][j].items.isEmpty()) {
                    System.out.print(level[i][j].getSymbol());
                } else if (!level[i][j].items.isEmpty()) {
                    System.out.print('i');
                } else if (entity instanceof Player) {
                    System.out.print('@');
                } else {
                    System.out.print('o');
                }
            }

            System.out.println();
        }
    }
}
