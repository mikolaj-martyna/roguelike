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

import java.io.PrintWriter;
import java.util.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Map {
    int currentLevelNumber = 0;
    Random random;
    private List<Level> levels;

    public Map() {
        this(24, 80);
    }

    public Map(int rows, int cols) {
        this.levels = new ArrayList<>();

        random = new Random();

        generateMap(100, rows, cols);
    }

    /* Misc */
    public static int distance(@NotNull Entity entity, @NotNull Player player) {
        return (int)
                sqrt(pow(entity.getX() - player.getX(), 2) + pow(entity.getY() - player.getY(), 2));
    }

    public void nextLevel() {
        this.currentLevelNumber++;
    }

    /* Getters */
    public int getRows() {
        return levels.get(currentLevelNumber).getRows();
    }

    public int getCols() {
        return levels.get(currentLevelNumber).getCols();
    }

    public int getRows(int level) {
        return levels.get(level).getRows();
    }

    public int getCols(int level) {
        return levels.get(level).getCols();
    }

    public Field[][] getFields() {
        return levels.get(currentLevelNumber).getFields();
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
        return getFields(currentLevelNumber)[x][y].getItems();
    }

    public List<Item> getItems(int level, int x, int y) {
        return getFields(level)[x][y].getItems();
    }

    public Level getCurrentLevel() {
        return levels.get(currentLevelNumber);
    }

    public int getCurrentStartingX() {
        return getCurrentLevel().getStartingX();
    }

    public int getCurrentStartingY() {
        return getCurrentLevel().getStartingY();
    }

    /* Checks */
    public boolean isInBounds(int x, int y) {
        return x >= 0
                && y >= 0
                && x < getRows(currentLevelNumber)
                && y < getCols(currentLevelNumber);
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
        return canPlaceEntity(currentLevelNumber, x, y);
    }

    public boolean canPlaceEntity(int level, int x, int y) {
        if (!isInBounds(level, x, y)) return false;
        if (getFields(level)[x][y].entity != null) return false;
        return (getFields(level)[x][y] instanceof Floor
                || getFields(level)[x][y] instanceof Bridge);
    }

    public boolean hasItem(int x, int y) {
        return hasItem(currentLevelNumber, x, y);
    }

    public boolean hasItem(int level, int x, int y) {
        return isInBounds(level, x, y) && !this.getFields(level)[x][y].items.isEmpty();
    }

    public boolean hasMonster(int x, int y) {
        return hasMonster(currentLevelNumber, x, y);
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

    public boolean placeEntity(int x, int y, Entity entity) {
        return placeEntity(currentLevelNumber, x, y, entity);
    }

    public boolean placeEntity(int level, int x, int y, Entity entity) {
        if (!canPlaceEntity(level, x, y)) return false;

        getFields(level)[x][y].entity = entity;
        getEntities(level).add(entity);

        entity.setX(x);
        entity.setY(y);

        return true;
    }

    public void removeItems(int x, int y) {
        removeItems(currentLevelNumber, x, y);
    }

    public void removeItems(int level, int x, int y) {
        getItems(level).removeAll(getFields(level)[x][y].getItems());
        getFields(level)[x][y].items.clear();
    }

    public void removeEntity(Entity entity) {
        removeEntity(currentLevelNumber, entity);
    }

    public void removeEntity(int level, @NotNull Entity entity) {
        getFields(level)[entity.getX()][entity.getY()].entity = null;

        getEntities(level).remove(entity);
    }

    public void removeEntity(int x, int y) {
        removeEntity(currentLevelNumber, x, y);
    }

    public void removeEntity(int level, int x, int y) {
        getEntities(level).remove(getFields(level)[x][y].entity);

        getFields(level)[x][y].entity = null;
    }

    public void removeEntity(int x, int y, Entity entity) {
        removeEntity(currentLevelNumber, x, y, entity);
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

        Entity entity = new Entity();

        entity.setX(random.nextInt(level.getRows()));
        entity.setY(random.nextInt(level.getCols()));

        // Get them to move <i, j> steps in random directions
        int minSteps = 250;
        int maxSteps = 640;

        int steps = random.nextInt(maxSteps - minSteps) + minSteps;

        for (int i = 0; i <= steps; i++) {
            boolean moveX = random.nextBoolean();

            if (moveX) {
                int newX =
                        Math.max(
                                Math.min(
                                        entity.getX() + random.nextInt(3) - 1, level.getRows() - 1),
                                0);

                entity.setX(newX);
            } else {
                int newY =
                        Math.max(
                                Math.min(
                                        entity.getY() + random.nextInt(3) - 1, level.getCols() - 1),
                                0);

                entity.setY(newY);
            }

            // Each field walked on by the entity is considered land
            // If walked twice on the same tile, don't decrease number of steps left
            if (level.getFields()[entity.getX()][entity.getY()] instanceof Floor) {
                --i;
            } else {
                level.getFields()[entity.getX()][entity.getY()] = new Floor();
            }
        }

        levels.add(level);

        generateBridges(level);
        generateItems(level);
        generateEntities(level);
        generatePassage(level);
        generateStartingPosition(level);
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

        levels.add(level);

        generateBridges(level);
        generateItems(level);
        generateEntities(level);
        generatePassage(level);
        generateStartingPosition(level);
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

                entity.setX(x);
                entity.setY(y);
            } else {
                --i;
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

        return new int[] {x, y};
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

        for (int i = 0; i < getRows(currentLevelNumber); i++) {
            for (int j = 0; j < getCols(currentLevelNumber); j++) {
                Entity entity = getFields(currentLevelNumber)[i][j].entity;

                if (entity != null) {
                    System.out.print(entity.getSymbol());
                } else if (!getFields(currentLevelNumber)[i][j].items.isEmpty()) {
                    System.out.print('i');
                } else {
                    System.out.print(getFields()[i][j].getSymbol());
                }
            }

            System.out.println();
        }
    }

    public void print(@NotNull PrintWriter output) {
        output.print("\033[H\033[2J");
        output.flush();

        Player player = null;

        for (int i = 0; i < getRows(currentLevelNumber); i++) {
            for (int j = 0; j < getCols(currentLevelNumber); j++) {
                Entity entity = getFields(currentLevelNumber)[i][j].entity;

                if (entity != null) {
                    output.printf("%c", entity.getSymbol());

                    if (entity instanceof Player) player = (Player) entity;
                } else if (!getFields(currentLevelNumber)[i][j].items.isEmpty()) {
                    output.printf("%c", 'i');
                } else {
                    output.printf("%c", getFields()[i][j].getSymbol());
                }
            }

            output.printf("\n");
        }

        if (player != null) {
            output.printf(
                    "HP: %d\tATK: %d\tAGL: %d\tDEF: %d\tINT: %d\tCHA: %d\n",
                    player.getHealth(),
                    player.getAttack(),
                    player.getAgility(),
                    player.getDefense(),
                    player.getIntelligence(),
                    player.getCharisma());
        }
    }
}
