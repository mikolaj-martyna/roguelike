package pl.umcs.map;

import static java.lang.Math.*;

import lombok.*;

import org.jetbrains.annotations.NotNull;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Player;
import pl.umcs.entities.monsters.*;
import pl.umcs.items.Item;
import pl.umcs.items.chestplates.Chestplate;
import pl.umcs.items.helms.Helm;
import pl.umcs.items.shoes.Shoes;
import pl.umcs.items.special_items.SpecialItem;
import pl.umcs.items.weapons.Weapon;
import pl.umcs.map.walls.*;

import java.io.PrintWriter;
import java.util.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Map {
    int currentLevel = 0;
    Random random;
    private List<Level> levels;

    public Map() {
        this(24, 80);
    }

    public Map(int rows, int cols) {
        this.levels = new ArrayList<>();

        random = new Random();

        generateMap(1, rows, cols);
    }

    /* Misc */
    public static int distance(@NotNull Entity entity, @NotNull Player player) {
        return (int)
                sqrt(pow(entity.getX() - player.getX(), 2) + pow(entity.getY() - player.getY(), 2));
    }

    /* Getters */
    public int getRows() {
        return levels.get(currentLevel).getRows();
    }

    public int getCols() {
        return levels.get(currentLevel).getCols();
    }

    public int getRows(int level) {
        return levels.get(level).getRows();
    }

    public int getCols(int level) {
        return levels.get(level).getCols();
    }

    public Field[][] getFields() {
        return levels.get(currentLevel).getFields();
    }

    public Field[][] getFields(int level) {
        return levels.get(level).getFields();
    }

    public List<Item> getItems(int level) {
        return levels.get(level).getItems();
    }

    public List<Entity> getEntities(int level) {
        return levels.get(level).getEntities();
    }

    public List<Item> getItems(int x, int y) {
        return getFields(currentLevel)[x][y].getItems();
    }

    public List<Item> getItems(int level, int x, int y) {
        return getFields(level)[x][y].getItems();
    }

    public Level getCurrentLevel() {
        return levels.get(currentLevel);
    }

    /* Checks */
    public boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < getRows(currentLevel) && y < getCols(currentLevel);
    }

    public boolean isInBounds(int level, int x, int y) {
        return x >= 0 && y >= 0 && x < getRows(level) && y < getCols(level);
    }

    public boolean canPlaceItem(int level, int x, int y) {
        return isInBounds(level, x, y)
                && (getFields(level)[x][y] instanceof Floor
                        || getFields(level)[x][y] instanceof Bridge)
                && getFields(level)[x][y].items.isEmpty();
    }

    public boolean canPlaceEntity(int x, int y) {
        return canPlaceEntity(currentLevel, x, y);
    }

    public boolean canPlaceEntity(int level, int x, int y) {
        if (!isInBounds(level, x, y)) return false;
        if (getFields(level)[x][y].entity != null) return false;
        return (getFields(level)[x][y] instanceof Floor
                || getFields(level)[x][y] instanceof Bridge);
    }

    public boolean hasItem(int x, int y) {
        return isInBounds(currentLevel, x, y)
                && !this.getFields(currentLevel)[x][y].items.isEmpty();
    }

    public boolean hasItem(int level, int x, int y) {
        return isInBounds(level, x, y) && !this.getFields(level)[x][y].items.isEmpty();
    }

    public boolean hasMonster(int x, int y) {
        return isInBounds(currentLevel, x, y) && this.getFields(currentLevel)[x][y].entity != null;
    }

    public boolean hasMonster(int level, int x, int y) {
        return isInBounds(level, x, y) && this.getFields(level)[x][y].entity != null;
    }

    /* Utility */
    public void placeItem(int level, int x, int y, Item item) {
        if (!canPlaceItem(level, x, y)) return;

        getFields(level)[x][y].items.add(item);
        getItems(level).add(item);
    }

    public void placeEntity(int x, int y, Entity entity) {
        placeEntity(currentLevel, x, y, entity);
    }

    public void placeEntity(int level, int x, int y, Entity entity) {
        if (!canPlaceEntity(level, x, y)) return;

        getFields(level)[x][y].entity = entity;
        getEntities(level).add(entity);

        entity.setX(x);
        entity.setY(y);
    }

    public void removeItems(int x, int y) {
        getItems(currentLevel).removeAll(getFields(currentLevel)[x][y].getItems());
        getFields(currentLevel)[x][y].items.clear();
    }

    public void removeItems(int level, int x, int y) {
        getItems(level).removeAll(getFields(level)[x][y].getItems());
        getFields(level)[x][y].items.clear();
    }

    public void removeEntity(Entity entity) {
        removeEntity(currentLevel, entity);
    }

    public void removeEntity(int level, Entity entity) {
        getEntities(level).remove(entity);

        getFields(level)[entity.getX()][entity.getY()].entity = null;

        entity.setX(-1);
        entity.setY(-1);
    }

    public void removeEntity(int x, int y) {
        removeEntity(currentLevel, x, y);
    }

    public void removeEntity(int level, int x, int y) {
        getEntities(level).remove(getFields(level)[x][y].entity);

        getFields(level)[x][y].entity.setX(-1);
        getFields(level)[x][y].entity.setY(-1);

        getFields(level)[x][y].entity = null;
    }

    public void removeEntity(int x, int y, Entity entity) {
        removeEntity(currentLevel, x, y, entity);
    }

    public void removeEntity(int level, int x, int y, Entity entity) {
        if (getFields(level)[x][y].entity == entity) {
            getEntities(level).remove(getFields(level)[x][y].entity);
            getFields(level)[x][y].entity = null;

            entity.setX(-1);
            entity.setY(-1);
        }
    }

    public void changeFieldType(int level, int x, int y, Field field) {
        getFields(level)[x][y] = field;
    }

    /* Generators */
    public void generateMap(int numberOfLevels, int rows, int cols) {
        for (int i = 0; i < numberOfLevels; i++) {
            int random = new Random().nextInt(101);

            if (random % 2 == 1) generateLevelIsland(rows, cols);
            else generateLevelDungeon(rows, cols);
        }
    }

    public void generateLevelIsland(int rows, int cols) {
        Level level = generateEmptyLevel(rows, cols);

        // Spawn n entities in random places
        List<Entity> entities = new ArrayList<>();
        int islands = random.nextInt(7) + 2;

        for (int i = 1; i <= islands; i++) {
            Entity entity = new Entity();

            entity.setX(random.nextInt(level.getRows()));
            entity.setY(random.nextInt(level.getCols()));

            entities.add(entity);
        }

        // Get them to move <i, j> steps in random directions
        int minSteps = 30;
        int maxSteps = 100;

        for (Entity entity : entities) {
            int steps = random.nextInt(maxSteps - minSteps) + minSteps;

            for (int i = 0; i <= steps; i++) {
                boolean moveX = random.nextBoolean();

                if (moveX) {
                    int newX =
                            Math.max(
                                    Math.min(
                                            entity.getX() + random.nextInt(3) - 1,
                                            level.getRows() - 1),
                                    0);

                    entity.setX(newX);
                } else {
                    int newY =
                            Math.max(
                                    Math.min(
                                            entity.getY() + random.nextInt(3) - 1,
                                            level.getCols() - 1),
                                    0);

                    entity.setY(newY);
                }

                // Each field walked on by the entity is considered land
                // If walked twice on the same tile, don't decrease number of steps left
                if (level.getFields()[entity.getX()][entity.getY()] instanceof Floor) {
                    //                    if (random.nextBoolean()) i--;
                    --i;
                } else {
                    level.getFields()[entity.getX()][entity.getY()] = new Floor();
                }
            }
        }

        generateBridges(level);
        generateItems(level);
        generateEntities(level);
        generatePassage(level);
        generateStartingPosition(level);

        levels.add(level);
    }

    public void generateLevelDungeon(int rows, int cols) {
        Level level = generateEmptyLevel(rows, cols);

        // Generate n points on the map
        // For each point, generate a room with x and y dimensions in range <i, j> and <k, l>
        int islands = random.nextInt(10) + 1;

        int minX = 5;
        int maxX = 10;
        int minY = 5;
        int maxY = 17;

        for (int i = 1; i <= islands; i++) {
            int x = random.nextInt(rows - minX);
            int y = random.nextInt(cols - minY);

            int dimensionX = random.nextInt(maxX - minX) + minX;
            int dimensionY = random.nextInt(maxY - minY) + minY;

            generateRoom(level, x, y, dimensionX, dimensionY);
        }

        generateBridges(level);
        generateItems(level);
        generateEntities(level);
        generatePassage(level);
        generateStartingPosition(level);

        levels.add(level);
    }

    public void generateRoom(@NotNull Level level, int x, int y, int dimensionX, int dimensionY) {
        for (int i = 0; i < level.getRows(); i++) {
            for (int j = 0; j < level.getCols(); j++) {
                if (i >= x && i <= x + dimensionX && j >= y && j <= y + dimensionY) {
                    level.getFields()[i][j] = new Floor();
                }
            }
        }
    }

    public void generateBridges(Level level) {}

    public Item generateItem() {
        // TODO: this is temporary, will be fixed
        ArrayList<Item> items =
                new ArrayList<>(
                        Arrays.asList(
                                new Chestplate(),
                                new Helm(),
                                new Shoes(),
                                new SpecialItem(),
                                new Weapon()));

        return items.get(random.nextInt(items.size()));
    }

    public void generateItems(Level level) {
        int items = (random.nextInt(12) + 3) / (random.nextBoolean() ? 2 : 1);

        for (int i = 1; i <= items; i++) {
            Item item = generateItem();

            int x = random.nextInt(level.getRows());
            int y = random.nextInt(level.getCols());

            if (level.getFields()[x][y] instanceof Floor
                    || level.getFields()[x][y] instanceof Bridge) {
                level.getFields()[x][y].items.add(item);
                level.getItems().add(item);
            } else {
                i--;
            }
        }
    }

    public Entity generateEntity() {
        // TODO: this is temporary, will be fixed
        ArrayList<Entity> entities =
                new ArrayList<>(
                        Arrays.asList(
                                new ClockworkDragon(),
                                new Inevitable(),
                                new Inevitable(),
                                new Modron(),
                                new Modron(),
                                new Modron(),
                                new Myr(),
                                new Myr(),
                                new Myr(),
                                new Myr(),
                                new Myr(),
                                new Myr(),
                                new Myr(),
                                new ThoughtEater(),
                                new ThoughtEater(),
                                new ThoughtEater(),
                                new ThoughtEater(),
                                new ThoughtEater(),
                                new ThoughtEater(),
                                new ThoughtEater(),
                                new ThoughtEater(),
                                new ThoughtEater(),
                                new ThoughtEater(),
                                new ThoughtEater(),
                                new ThoughtEater(),
                                new ThoughtEater()));

        return entities.get(random.nextInt(entities.size()));
    }

    public void generateEntities(Level level) {
        int entities = (random.nextInt(12) + 3) / (random.nextBoolean() ? 2 : 1);

        for (int i = 1; i <= entities; i++) {
            Entity entity = generateEntity();

            int x = random.nextInt(level.getRows());
            int y = random.nextInt(level.getCols());

            if (level.getFields()[x][y] instanceof Floor
                    || level.getFields()[x][y] instanceof Bridge) {
                level.getFields()[x][y].entity = entity;
                level.getEntities().add(entity);
            } else {
                i--;
            }
        }
    }

    public int[] chooseValidPosition(@NotNull Level level) {
        int x;
        int y;

        do {
            x = random.nextInt(level.getRows());
            y = random.nextInt(level.getCols());
        } while (!(level.getFields()[x][y] instanceof Floor
                || level.getFields()[x][y] instanceof Bridge));

        return new int[]{x, y};
    }

    public void generatePassage(@NotNull Level level) {
        int[] position = chooseValidPosition(level);

        level.getFields()[position[0]][position[1]] = new Passage();
    }

    public void generateStartingPosition(@NotNull Level level) {
        int[] position = chooseValidPosition(level);

        level.setStartingX(position[0]);
        level.setStartingY(position[1]);
    }

    public Level generateEmptyLevel(int rows, int cols) {
        Field[][] fields = new Field[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                fields[i][j] = new Void();
            }
        }

        return new Level(fields, rows, cols);
    }

    /* Printing */
    public void print() {
        System.out.flush();

        for (int i = 0; i < getRows(currentLevel); i++) {
            for (int j = 0; j < getCols(currentLevel); j++) {
                Entity entity = getFields(currentLevel)[i][j].entity;

                if (entity == null && getFields(currentLevel)[i][j].items.isEmpty()) {
                    System.out.print(getFields(currentLevel)[i][j].getSymbol());
                } else if (entity instanceof Player) {
                    System.out.print('@');
                } else if (!getFields(currentLevel)[i][j].items.isEmpty()) {
                    System.out.print('i');
                } else {
                    System.out.print('o');
                }
            }

            System.out.println();
        }
    }

    public void print(@NotNull PrintWriter output) {
        output.print("\033[H\033[2J");
        output.flush();

        Player player = null;

        for (int i = 0; i < getRows(currentLevel); i++) {
            for (int j = 0; j < getCols(currentLevel); j++) {
                Entity entity = getFields(currentLevel)[i][j].entity;

                if (entity == null && getFields(currentLevel)[i][j].items.isEmpty()) {
                    output.printf("%c", getFields(currentLevel)[i][j].getSymbol());
                } else if (entity instanceof Player) {
                    player = (Player) entity;
                    output.printf("%c", '@');
                } else if (!getFields(currentLevel)[i][j].items.isEmpty()) {
                    output.printf("%c", 'i');
                } else {
                    output.printf("%c", 'o');
                }
            }

            output.printf("\n");
        }

        if (player != null) {
            output.printf(
                    "HP: %d\tATK: %d\tAGL: %d\tDEF: %d\tINT: %d\tCHA: %d\n",
                    player.getHealth(),
                    player.getAttack().getCurrent(),
                    player.getAgility().getCurrent(),
                    player.getDefense().getCurrent(),
                    player.getIntelligence().getCurrent(),
                    player.getCharisma().getCurrent());
        }
    }
}
