package pl.umcs.map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import lombok.*;

import org.jetbrains.annotations.NotNull;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Player;
import pl.umcs.items.Item;
import pl.umcs.map.walls.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    public static int distance(@NotNull Entity entity, @NotNull Player player) {
        return (int) sqrt(pow(entity.getX() - player.getX(), 2) + pow(entity.getY() - player.getY(), 2));
    }

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

    public void removeItems(int x, int y) {
        this.items.removeAll(level[x][y].getItems());
        level[x][y].items.clear();
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);

        level[entity.getX()][entity.getY()].entity = null;

        entity.setX(-1);
        entity.setY(-1);
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

                if (c == '░' || c == '.') {
                    field = new Floor();
                } else if (c == '═' || c == '-') {
                    field = new HorizontalWall();
                } else if (c == '║' || c == '|') {
                    field = new VerticalWall();
                } else if (c == '╔') {
                    field = new UpperLeftWallCorner();
                } else if (c == '╗') {
                    field = new UpperRightWallCorner();
                } else if (c == '╚') {
                    field = new LowerLeftWallCorner();
                } else if (c == '╝') {
                    field = new LowerRightWallCorner();
                } else if (c == '#') {
                    field = new Bridge();
                } else {
                    field = new Void();
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
                } else if (entity instanceof Player) {
                    System.out.print('@');
                } else if (!level[i][j].items.isEmpty()) {
                    System.out.print('i');
                } else {
                    System.out.print('o');
                }
            }

            System.out.println();
        }
    }

    public boolean hasItem(int x, int y) {
        return isInBounds(x, y) && !this.level[x][y].items.isEmpty();
    }

    public boolean hasMonster(int x, int y) {
        return isInBounds(x, y) && this.level[x][y].entity != null;
    }

    public List<Item> getItems(int x, int y) {
        return level[x][y].getItems();
    }

    public void print(@NotNull PrintWriter output) {
        output.print("\033[H\033[2J");
        output.flush();

        Player player = null;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Entity entity = level[i][j].entity;

                if (entity == null && level[i][j].items.isEmpty()) {
                    output.printf("%c", level[i][j].getSymbol());
                } else if (entity instanceof Player) {
                    player = (Player) entity;
                    output.printf("%c", '@');
                } else if (!level[i][j].items.isEmpty()) {
                    output.printf("%c", 'i');
                } else {
                    output.printf("%c", 'o');
                }
            }

            output.printf("\n");
        }

        if (player != null) {
            output.printf("HP: %d\tATK: %d\tAGL: %d\tDEF: %d\tINT: %d\tCHA: %d\n",
                    player.getHealth(),
                    player.getAttack().getCurrent(),
                    player.getAgility().getCurrent(),
                    player.getDefense().getCurrent(),
                    player.getIntelligence().getCurrent(),
                    player.getCharisma().getCurrent());
        }
    }
}
