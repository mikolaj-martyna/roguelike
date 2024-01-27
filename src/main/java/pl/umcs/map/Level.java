package pl.umcs.map;

import lombok.Getter;
import lombok.Setter;
import pl.umcs.entities.Entity;
import pl.umcs.items.Item;

import java.util.ArrayList;

@Getter
@Setter
public class Level {
    private final int rows; // [rows][    ]
    private final int cols; // [    ][cols]

    Field[][] fields;

    private ArrayList<Entity> entities;
    private ArrayList<Item> items;

    private int startingX;
    private int startingY;

    public Level(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.fields = new Field[rows][cols];

        entities = new ArrayList<>();
        items = new ArrayList<>();
    }

    public Level(Field[][] fields, int rows, int cols) {
        this(rows, cols);

        this.fields = fields;
    }

    public Level(Field[][] fields, int rows, int cols, ArrayList<Item> items, ArrayList<Entity> entities) {
        this(fields, rows, cols);

        this.entities = entities;
        this.items = items;
    }
}
