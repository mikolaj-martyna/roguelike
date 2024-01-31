package pl.umcs.map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import pl.umcs.Graph;
import pl.umcs.entities.Entity;
import pl.umcs.entities.Player;
import pl.umcs.entities.monsters.*;
import pl.umcs.items.Item;
import pl.umcs.items.chestplates.Chainmail;
import pl.umcs.items.chestplates.ClothArmor;
import pl.umcs.items.chestplates.CrystallineArmor;
import pl.umcs.items.consumables.HealthPotion;
import pl.umcs.items.helms.BasicHelm;
import pl.umcs.items.helms.HelmetOfEnlightenment;
import pl.umcs.items.shoes.AetherShoes;
import pl.umcs.items.shoes.Sandals;
import pl.umcs.items.shoes.WornOutShoes;
import pl.umcs.items.special_items.AncientScroll;
import pl.umcs.items.special_items.EternalDynamo;
import pl.umcs.items.special_items.Feather;
import pl.umcs.items.weapons.ClockworkAxe;
import pl.umcs.items.weapons.CommonSword;
import pl.umcs.items.weapons.Stick;
import pl.umcs.map.walls.HorizontalWall;
import pl.umcs.map.walls.VerticalWall;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Map {
    int currentLevelNumber = 0;
    int levelsAmount;

    Random random;

    private List<Level> levels;

    public Map() {
        this(100, 80, 24);
    }

    public Map(int levelsAmount) {
        this(levelsAmount, 80, 24);
    }

    public Map(int levelsAmount, int width, int height) {
        this.levels = new ArrayList<>();
        this.levelsAmount = levelsAmount;

        this.random = new Random();

        generateMap(levelsAmount, width, height);
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

    public Field getField(int x, int y) {
        return getField(currentLevelNumber, x, y);
    }

    public Field getField(int level, int x, int y) {
        return levels.get(level).getFields()[x][y];
    }

    public List<Item> getItems(int level) {
        return levels.get(level).getItems();
    }

    public List<Entity> getEntities() {
        return levels.get(currentLevelNumber).getEntities();
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

    public Level getLevel(int level) {
        return levels.get(level);
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

    public boolean isInBounds(Level level, int x, int y) {
        return x >= 0 && y >= 0 && x < level.getWidth() && y < level.getHeight();
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

    public boolean canPlaceEntity(Level level, int x, int y) {
        if (!isInBounds(level, x, y)) return false;
        if (level.getFields()[x][y].entity != null) return false;

        return (level.getFields()[x][y] instanceof Floor
                || level.getFields()[x][y] instanceof Bridge
                || level.getFields()[x][y] instanceof Door);
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
    public void placeItem(int x, int y, Item item) {
        placeItem(currentLevelNumber, x, y, item);
    }

    public void placeItem(int level, int x, int y, Item item) {
        if (!canPlaceItem(level, x, y)) return;

        getFields(level)[x][y].items.add(item);
        getItems(level).add(item);
    }

    public boolean placeEntity(int x, int y, Entity entity) {
        return placeEntity(getCurrentLevel(), x, y, entity);
    }

    public boolean placeEntity(Level level, int x, int y, Entity entity) {
        if (!canPlaceEntity(level, x, y)) return false;

        level.getFields()[x][y].entity = entity;
        level.getEntities().add(entity);

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
        removeEntity(level, entity.getX(), entity.getY(), entity);
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
            if (currentLevel % Math.max((levelsAmount / 10), 1) == 0)
                generateLevelIsland(width, height);
            else generateLevelDungeon(width, height);
        }
    }

    public void generateLevelIsland(int width, int height) {
        Level level = generateEmptyLevel(width, height);

        Entity entity = new Entity();

        entity.setX(random.nextInt(level.getWidth()));
        entity.setY(random.nextInt(level.getHeight()));

        int minSteps = (width * height) / 5;
        int maxSteps = (int) ((width * height) / 1.25);

        int stepsToTake = random.nextInt(maxSteps - minSteps) + minSteps;

        for (int currentSteps = 0; currentSteps <= stepsToTake; currentSteps++) {
            boolean willMoveX = random.nextBoolean();

            if (willMoveX) {
                int newX = validateX(entity.getX() + random.nextInt(3) - 1, width);

                entity.setX(newX);
            } else {
                int newY = validateY(entity.getY() + random.nextInt(3) - 1, height);

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

        generateItems(level, this.getLevels().size());
        generateEntities(level, this.getLevels().size());
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
            //            boolean isGone = goneRooms < 4 && random.nextBoolean();
            boolean isGone = false;
            if (isGone) goneRooms++;

            generateRoom(level, currentRoomNumber, isGone);
        }

        for (int currentRoom = 0; currentRoom < roomsAmount; currentRoom++) {
            if (currentRoom + 3 < roomsAmount) {
                Room roomOne = level.getRooms().get(currentRoom);
                Room roomTwo = level.getRooms().get(currentRoom + 3);

                generateBridgeBetweenRooms(level, roomOne, roomTwo);
            }

            if ((currentRoom + 1) % 3 != 0) {
                Room roomOne = level.getRooms().get(currentRoom);
                Room roomTwo = level.getRooms().get(currentRoom + 1);

                generateBridgeBetweenRooms(level, roomOne, roomTwo);
            }
        }

        levels.add(level);

        generateItems(level, this.getLevels().size());
        generateEntities(level, this.getLevels().size());
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

        // Generate doors
        int doorOneX;
        int doorOneY;

        int doorTwoX;
        int doorTwoY;

        boolean isGoingRight = roomOne.getId() + 1 == roomTwo.getId();
        boolean isGoingDown = roomOne.getId() + 3 == roomTwo.getId();

        if (isGoingRight) {
            doorOneX = roomOne.getEndX();
            doorOneY = random.nextInt(roomOne.getHeight() - 2) + roomOne.getStartY() + 1;

            doorTwoX = roomTwo.getStartX();
            doorTwoY = random.nextInt(roomTwo.getHeight() - 2) + roomTwo.getStartY() + 1;

            int distance = Math.abs(doorTwoX - doorOneX);
            int[] delta = new int[]{1, 0};

            int turnDistance = Math.abs(doorTwoY - doorOneY);
            int[] deltaTurn = doorOneY < doorTwoY ? new int[]{0, 1} : new int[]{0, -1};

            drawBridge(level, doorOneX, doorOneY, distance, delta, turnDistance, deltaTurn);
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

            int distance = Math.abs(doorTwoY - doorOneY);
            int[] delta = new int[]{0, 1};

            int turnDistance = Math.abs(doorTwoX - doorOneX);
            int[] deltaTurn = doorOneX < doorTwoX ? new int[]{1, 0} : new int[]{-1, 0};

            drawBridge(level, doorOneX, doorOneY, distance, delta, turnDistance, deltaTurn);
        } else {
            return;
        }

        Door doorOne = new Door(doorOneX, doorOneY);
        Door doorTwo = new Door(doorTwoX, doorTwoY);

        roomOne.addDoor(doorOne);
        roomTwo.addDoor(doorTwo);

        level.changeFieldType(doorOneX, doorOneY, doorOne);
        level.changeFieldType(doorTwoX, doorTwoY, doorTwo);
    }

    public void drawBridge(
            Level level,
            int doorOneX,
            int doorOneY,
            int distance,
            int[] delta,
            int turnDistance,
            int[] deltaTurn) {
        int currentX = doorOneX;
        int currentY = doorOneY;

        int turningPoint = random.nextInt(distance - 1) + 2;

        while (distance > 0) {
            currentX += delta[0];
            currentY += delta[1];

            level.changeFieldType(currentX, currentY, new Bridge());

            if (distance == turningPoint) {
                while (turnDistance > 0) {
                    currentX += deltaTurn[0];
                    currentY += deltaTurn[1];

                    level.changeFieldType(currentX, currentY, new Bridge());

                    turnDistance--;
                }
            }

            distance--;
        }
    }

    public Item generateItem(int levelNumber) {
        ArrayList<Item> availableItems =
                new ArrayList<>(
                        Arrays.asList(
                                new ClothArmor(),
                                new BasicHelm(),
                                new WornOutShoes(),
                                new Feather(),
                                new Stick()));
        ArrayList<Item> itemsOverOneFourth =
                new ArrayList<>(Arrays.asList(new Chainmail(), new Sandals(), new CommonSword()));
        ArrayList<Item> itemsEndgame =
                new ArrayList<>(
                        Arrays.asList(
                                new CrystallineArmor(),
                                new HelmetOfEnlightenment(),
                                new AetherShoes(),
                                new AncientScroll(),
                                new ClockworkAxe()));

        if (levelNumber >= levelsAmount * 0.25) availableItems.addAll(itemsOverOneFourth);
        if (levelNumber >= levelsAmount * 0.5) availableItems.addAll(itemsEndgame);

        return availableItems.get(random.nextInt(availableItems.size()));
    }

    public void generateItems(Level level, int levelNumber) {
        int items = (random.nextInt(6) + 3) / (random.nextBoolean() ? 2 : 1);

        for (int i = 1; i <= items; i++) {
            Item item = generateItem(levelNumber);

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

        if (levelNumber == levelsAmount) {
            EternalDynamo eternalDynamo = new EternalDynamo();

            while (true) {
                int x = random.nextInt(level.getWidth());
                int y = random.nextInt(level.getHeight());

                if (level.getFields()[x][y] instanceof Floor
                        || level.getFields()[x][y] instanceof Bridge) {
                    level.getFields()[x][y].items.add(eternalDynamo);
                    level.getItems().add(eternalDynamo);

                    break;
                }
            }
        }
    }

    public Entity generateEntity(int levelNumber) {
        ArrayList<Entity> availableEntities =
                new ArrayList<>(Arrays.asList(new ThoughtEater(), new Myr()));
        ArrayList<Entity> entitiesUnderThreeFourths =
                new ArrayList<>(Arrays.asList(new Inevitable(), new Modron()));
        ArrayList<Entity> entitiesEndgame = new ArrayList<>(Arrays.asList(new ClockworkDragon()));

        if (levelNumber >= (levelsAmount * 0.25)) {
            availableEntities.addAll(entitiesUnderThreeFourths);
        }

        if (levelNumber >= (levelsAmount * 0.75)) {
            availableEntities.addAll(entitiesEndgame);
        }

        return availableEntities.get(random.nextInt(availableEntities.size()));
    }

    public void generateEntities(Level level, int levelNumber) {
        int entities = (random.nextInt(15) + 3) / (random.nextBoolean() ? 2 : 1);

        for (int i = 1; i <= entities; i++) {
            Entity entity = generateEntity(levelNumber);

            int x = random.nextInt(level.getWidth());
            int y = random.nextInt(level.getHeight());

            if (!placeEntity(level, x, y, entity)) {
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
        } while (!canPlaceEntity(level, x, y));

        return new int[]{x, y};
    }

    public void generatePassage(@NotNull Level level) {
        int[] position = chooseValidPosition(level);

        level.changeFieldType(position[0], position[1], new Passage());
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

    /* Misc */
    public List<Graph.Node> pathToPlayer(@NotNull Entity entity, @NotNull Player player) {
        return Graph.BFS(
                this,
                new Graph.Node(entity.getX(), entity.getY(), 0),
                new Graph.Node(player.getX(), player.getY(), -1));
    }

    public Graph.Node nextMove(@NotNull Entity entity, @NotNull Player player) {
        return pathToPlayer(entity, player).get(0);
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
        int height = getHeight();
        int width = getWidth();

        for (int currentY = 0; currentY < height; currentY++) {
            for (int currentX = 0; currentX < width; currentX++) {
                Field field = getField(currentX, currentY);
                Entity entity = field.getEntity();

                if (entity != null) {
                    output.printf("\033[0;1m%c\033[0m", entity.getSymbol());
                } else if (!field.getItems().isEmpty()) {
                    if (field.getItems().stream().filter(i -> i instanceof HealthPotion).toList().isEmpty()) {
                        output.printf("%c", 'i');
                    } else {
                        output.printf("%c", '*');
                    }
                } else {
                    output.printf("%c", field.getSymbol());
                }
            }

            output.printf("\n");
        }
    }

    public void printSummary(PrintWriter output, @NotNull Player player) {
        if (!player.isAlive()) output.printf("You died.\n");

        if (player.getEquipment().getSpecialItem() instanceof EternalDynamo
                || player.getEquipment().getItems().stream()
                .anyMatch(item -> item instanceof EternalDynamo)) {
            output.printf(
                    "You've found the Eternal Dynamo! You can head back to your island now. Rest now, you did well.");
        } else {
            output.printf(
                    "You've failed this time, but worry not! You can search again whenever you like.");
        }

        output.flush();
    }

    /* Validators */
    public int validateX(int x) {
        return Math.max(Math.min(x, this.getWidth() - 1), 0);
    }

    public int validateX(int x, int width) {
        return Math.max(Math.min(x, width - 1), 0);
    }

    public int validateY(int y) {
        return Math.max(Math.min(y, this.getHeight() - 1), 0);
    }

    public int validateY(int y, int height) {
        return Math.max(Math.min(y, height - 1), 0);
    }
}
