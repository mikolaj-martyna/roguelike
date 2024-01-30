package pl.umcs.map;

import lombok.Getter;
import lombok.Setter;

import org.jetbrains.annotations.NotNull;
import pl.umcs.entities.Entity;
import pl.umcs.items.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Getter
@Setter
public class Level {
    private final int width;
    private final int height;

    private Field[][] fields;
    private ArrayList<Room> rooms;

    private ArrayList<Entity> entities;
    private ArrayList<Item> items;

    private int startingX;
    private int startingY;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;

        this.fields = new Field[width][height];
        this.rooms = new ArrayList<>();

        this.entities = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public Level(Field[][] fields, int width, int height) {
        this(width, height);

        this.fields = fields;
    }

    public Level(
            Field[][] fields,
            int width,
            int height,
            ArrayList<Item> items,
            ArrayList<Entity> entities) {
        this(fields, width, height);

        this.entities = entities;
        this.items = items;
    }

    public void changeFieldType(int x, int y, Field field) {
        if (x < 0 || y < 0 || x >= 80 || y >= 24) return;
        if (this.getFields()[x][y] instanceof Door && field instanceof Bridge) return;
        this.getFields()[x][y] = field;
    }
}
