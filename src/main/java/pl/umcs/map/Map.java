package pl.umcs.map;

import lombok.*;

import org.jetbrains.annotations.NotNull;
import pl.umcs.Entity;
import pl.umcs.Item;

import java.util.ArrayList;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Map {
    // TODO: Remove or refactor when done better
    private Field[][] level;
    private int rows;
    private int cols;

    private ArrayList<Item> items;
    private ArrayList<Entity> entities;

    public boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x <= cols && y <= rows;
    }

    public boolean canPlaceItem(int x, int y) {
        return isInBounds(x, y)
                && (Objects.equals(level[x][y].getName(), "island")
                        || Objects.equals(level[x][y].getName(), "bridge"))
                && level[x][y].items.isEmpty();
    }

    public boolean canPlaceEntity(int x, int y) {
        if (!isInBounds(x, y)) return false;
        if (level[x][y].entity != null) return false;
        return Objects.equals(level[x][y].getName(), "island")
                || Objects.equals(level[x][y].getName(), "bridge");
    }

    public void placeItem(int x, int y, Item item) {
        if (!canPlaceItem(x, y)) return;

        level[x][y].items.add(item);
    }

    public void placeEntity(int x, int y, Entity entity) {
        if (!canPlaceEntity(x, y)) return;

        level[x][y].entity = entity;
    }

    public void removeItem(int x, int y, Item item) {
        level[x][y].items.remove(item);
    }

    public void removeEntity(int x, int y) {
        level[x][y].entity = null;
    }

    public void removeEntity(int x, int y, Entity entity) {
        if (level[x][y].entity == entity) level[x][y].entity = null;
    }

    // TODO: fix
    public void load(@NotNull String mapData) {
        //        String[] rows = mapData.split("\n");
        //
        //        int x = 0;
        //        for (String row : rows) {
        //            for (int i = 0; i < cols; i++) {
        //                Field field = Field.builder().symbol(row.charAt(i)).entity(null).items(new
        // ArrayList<Item>()).build();
        //                changeFieldType(x, i, field);
        //            }
        //            x++;
        //            this.rows = row.length();
        //        }
        //        this.cols = x - 1;
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
