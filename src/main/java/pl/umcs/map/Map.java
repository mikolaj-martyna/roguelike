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
import pl.umcs.map.walls.HorizontalWall;
import pl.umcs.map.walls.VerticalWall;

import java.io.PrintWriter;
import java.util.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Map {
    int currentLevelNumber = 0;
    private List<Level> levels;

    Random random;

    public Map() {
        this(80, 24);
    }

    public Map(int width, int height) {
        this.levels = new ArrayList<>();

        random = new Random();

        generateMap(100, width, height);
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
    public int getWidth() {
        return levels.get(currentLevelNumber).getWidth();
    }

    public int getHeight() {
        return levels.get(currentLevelNumber).getHeight();
    }

    public int getWidth(int level) {
        return levels.get(level).getWidth();
    }

    public int getHeight(int level) {
        return levels.get(level).getHeight();
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
                && x < getWidth(currentLevelNumber)
                && y < getHeight(currentLevelNumber);
    }

    public boolean isInBounds(int level, int x, int y) {
        return x >= 0 && y >= 0 && x < getWidth(level) && y < getHeight(level);
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
                || getFields(level)[x][y] instanceof Bridge
                || getFields(level)[x][y] instanceof Door);
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
    public void generateMap(int numberOfLevels, int width, int height) {
        for (int currentLevel = 0; currentLevel < numberOfLevels; currentLevel++) {
            //            if (currentLevel % 10 == 0) generateLevelIsland(width, height);
            //            else generateLevelDungeon(width, height);

            // TODO: remove after testing
            generateLevelDungeon(width, height);
        }
    }

    public void generateLevelIsland(int width, int height) {
        Level level = generateEmptyLevel(width, height);

        Entity entity = new Entity();

        entity.setX(random.nextInt(level.getWidth()));
        entity.setY(random.nextInt(level.getHeight()));

        // Get them to move <i, j> steps in random directions
        int minSteps = 250;
        int maxSteps = 640;

        int stepsToTake = random.nextInt(maxSteps - minSteps) + minSteps;

        for (int currentSteps = 0; currentSteps <= stepsToTake; currentSteps++) {
            boolean willMoveX = random.nextBoolean();

            if (willMoveX) {
                int newX =
                        Math.max(
                                Math.min(
                                        entity.getX() + random.nextInt(3) - 1,
                                        level.getWidth() - 1),
                                0);

                entity.setX(newX);
            } else {
                int newY =
                        Math.max(
                                Math.min(
                                        entity.getY() + random.nextInt(3) - 1,
                                        level.getHeight() - 1),
                                0);

                entity.setY(newY);
            }

            // Each field walked on by the entity is considered land
            // If walked twice on the same tile, don't decrease number of steps left
            if (level.getFields()[entity.getX()][entity.getY()] instanceof Floor) {
                --currentSteps;
            } else {
                level.getFields()[entity.getX()][entity.getY()] = new Floor();
            }
        }

        levels.add(level);

        generateItems(level);
        generateEntities(level);
        generatePassage(level);
        generateStartingPosition(level);
    }

    // Strongly based on original rogue dungeon generation (with min values tweaked slightly)
    // http://99.255.210.85/2019/06/03/rogue-level-generation.html
    public void generateLevelDungeon(int width, int height) {
        Level level = generateEmptyLevel(width, height);

        int roomsAmount = 9;
        int goneRooms = 0;

        for (int currentRoomNumber = 0; currentRoomNumber < roomsAmount; currentRoomNumber++) {
            boolean isGone = goneRooms < 4 && random.nextBoolean();
            if (isGone) goneRooms++;

            generateRoom(level, currentRoomNumber, isGone);
        }

        for (int currentRoom = 0; currentRoom < roomsAmount; currentRoom++) {
            for (int neighbourId : new int[] {currentRoom + 1, currentRoom + 3}) {
                if ((neighbourId % 3) != 0 && neighbourId < roomsAmount) {
                    Room roomOne = level.getRooms().get(currentRoom);
                    Room roomTwo = level.getRooms().get(neighbourId);

                    generateBridgeBetweenRooms(level, roomOne, roomTwo);
                }
            }
        }

        levels.add(level);

        generateItems(level);
        generateEntities(level);
        generatePassage(level);
        generateStartingPosition(level);
    }

    public void generateRoom(@NotNull Level level, int roomNumber, boolean isGone) {
        Room room = new Room();

        int minX = 6 + 1;
        int maxX = 26;
        int minY = 4 + 1;
        int maxY = 8;

        room.id = roomNumber;

        room.x = (roomNumber % 3) * 26 + 1;
        room.y = (roomNumber / 3) * 8;

        room.isGone = isGone;

        if (room.isGone) {
            room.width = 0;
            room.height = 0;

            room.xOffset = random.nextInt(24) + 1;
            room.yOffset = random.nextInt(6) + 1;
        } else {
            room.width = random.nextInt(maxX - minX - 1) + minX;
            room.height = random.nextInt(maxY - minY - 1) + minY;

            room.xOffset = random.nextInt(26 - room.width);
            room.yOffset = random.nextInt(8 - room.height);

            addRoomToMap(level, room);
        }

        level.getRooms().add(room);
    }

    public void addRoomToMap(@NotNull Level level, @NotNull Room room) {
        for (int currentX = room.getStartX(); currentX <= room.getEndX(); currentX++) {
            for (int currentY = room.getStartY(); currentY <= room.getEndY(); currentY++) {
                if (currentY == room.getStartY() || currentY == room.getEndY()) {
                    level.getFields()[currentX][currentY] = new HorizontalWall();
                } else if (currentX == room.getStartX() || currentX == room.getEndX()) {
                    level.getFields()[currentX][currentY] = new VerticalWall();
                } else {
                    level.getFields()[currentX][currentY] = new Floor();
                }
            }
        }
    }

    public void generateBridgeBetweenRooms(
            Level level, @NotNull Room roomOne, @NotNull Room roomTwo) {
        if (roomOne.getId() > roomTwo.getId()) {
            Room temp = roomOne;
            roomOne = roomTwo;
            roomTwo = temp;
        }

        // Tunnel right if ri1 + 1 == ri2
        // Tunnel down if ri1 + 5 == ri2

        // Generate doors
        int doorOneX;
        int doorOneY;

        int doorTwoX;
        int doorTwoY;

        boolean isGoingRight = roomOne.getId() + 1 == roomTwo.getId();
        boolean isGoingDown = roomOne.getId() + 3 == roomTwo.getId();

        if (isGoingRight) {
            doorOneX = roomOne.getEndX();
            doorOneY =
                    random.nextInt(roomOne.getEndY() - roomOne.getStartY() - 1)
                            + roomOne.getStartY()
                            + 1;

            doorTwoX = roomTwo.getStartX();
            doorTwoY =
                    random.nextInt(roomTwo.getEndY() - roomTwo.getStartY() - 1)
                            + roomTwo.getStartY()
                            + 1;
        } else if (isGoingDown) {
            doorOneX =
                    random.nextInt(roomOne.getEndX() - roomOne.getStartX() - 1)
                            + roomOne.getStartX()
                            + 1;
            doorOneY = roomOne.getEndY();

            doorTwoX =
                    random.nextInt(roomTwo.getEndX() - roomTwo.getStartX() - 1)
                            + roomTwo.getStartX()
                            + 1;
            doorTwoY = roomTwo.getStartY();
        } else {
            return;
        }

        Door doorOne = new Door(doorOneX, doorOneY);
        Door doorTwo = new Door(doorTwoX, doorTwoY);

        roomOne.addDoor(doorOne);
        roomTwo.addDoor(doorTwo);

        level.changeFieldType(doorOneX, doorOneY, doorOne);
        level.changeFieldType(doorTwoX, doorTwoY, doorTwo);

        int distanceX = Math.abs(doorTwoX - doorOneX);
        int distanceY = Math.abs(doorTwoY - doorOneY);

        int[] deltaX = isGoingRight ? new int[] {1, 0} : new int[] {0, 1};
        int[] deltaY =
                isGoingRight
                        ? (doorOneY < doorTwoY ? new int[] {0, 1} : new int[] {0, -1})
                        : (doorOneX < doorTwoX ? new int[] {1, 0} : new int[] {-1, 0});

        int currentX = doorOneX;
        int currentY = doorOneY;

        int turningPoint;
        if (distanceX > 0) turningPoint = distanceX == 1 ? 0 : random.nextInt(distanceX - 1);
        else return;

        while (distanceX > 0) {
            currentX += deltaX[0];
            currentY += deltaX[1];

            level.changeFieldType(currentX, currentY, new Bridge());

            if (distanceX == turningPoint) {
                while (distanceY > 0) {
                    currentX += deltaY[0];
                    currentY += deltaY[1];

                    level.changeFieldType(currentX, currentY, new Bridge());

                    //                    this.print(level);
                    distanceY--;
                }
            }

            //            this.print(level);
            distanceX--;
        }
    }

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

            int x = random.nextInt(level.getWidth());
            int y = random.nextInt(level.getHeight());

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

            int x = random.nextInt(level.getWidth());
            int y = random.nextInt(level.getHeight());

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
            x = random.nextInt(level.getWidth());
            y = random.nextInt(level.getHeight());
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

    public Level generateEmptyLevel(int width, int height) {
        Field[][] fields = new Field[width][height];

        for (int currentX = 0; currentX < width; currentX++) {
            for (int currentY = 0; currentY < height; currentY++) {
                fields[currentX][currentY] = new Void();
            }
        }

        return new Level(fields, width, height);
    }

    /* Printing */
    public void print() {
        System.out.flush();

        for (int currentY = 0; currentY < getHeight(currentLevelNumber); currentY++) {
            for (int currentX = 0; currentX < getWidth(currentLevelNumber); currentX++) {
                Entity entity = getFields(currentLevelNumber)[currentX][currentY].entity;

                if (entity != null) {
                    System.out.print(entity.getSymbol());
                } else if (!getFields(currentLevelNumber)[currentX][currentY].items.isEmpty()) {
                    System.out.print('i');
                } else {
                    System.out.print(getFields()[currentX][currentY].getSymbol());
                }
            }

            System.out.println();
        }
    }

    public void print(@NotNull Level level) {
        System.out.flush();

        for (int currentY = 0; currentY < level.getHeight(); currentY++) {
            for (int currentX = 0; currentX < level.getWidth(); currentX++) {
                Entity entity = level.getFields()[currentX][currentY].entity;

                if (entity != null) {
                    System.out.print(entity.getSymbol());
                } else if (!level.getFields()[currentX][currentY].items.isEmpty()) {
                    System.out.print('i');
                } else {
                    System.out.print(level.getFields()[currentX][currentY].getSymbol());
                }
            }

            System.out.println();
        }
    }

    public void print(@NotNull PrintWriter output) {
        output.print("\033[H\033[2J");
        output.flush();

        Player player = null;

        for (int currentY = 0; currentY < getHeight(currentLevelNumber); currentY++) {
            for (int currentX = 0; currentX < getWidth(currentLevelNumber); currentX++) {
                Entity entity = getFields(currentLevelNumber)[currentX][currentY].entity;

                if (entity != null) {
                    output.printf("%c", entity.getSymbol());

                    if (entity instanceof Player) player = (Player) entity;
                } else if (!getFields(currentLevelNumber)[currentX][currentY].items.isEmpty()) {
                    output.printf("%c", 'i');
                } else {
                    output.printf("%c", getFields()[currentX][currentY].getSymbol());
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
