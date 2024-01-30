package pl.umcs.map;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@Getter
@Setter
public class Room {
    int id;

    int x;
    int y;

    int xOffset;
    int yOffset;

    int width;
    int height;

    boolean isGone;

    private ArrayList<Door> doors;

    public Room() {
        this(0, 0, 0, 0, 0, 0, 0);
    }

    public Room(int id, int x, int y, int xOffset, int yOffset, int width, int height) {
        this(id, x, y, xOffset, yOffset, width, height, false);
    }

    public Room(int id, int x, int y, int xOffset, int yOffset, int width, int height, boolean isGone) {
        this.id = id;

        this.x = x;
        this.y = y;

        this.xOffset = xOffset;
        this.yOffset = yOffset;

        this.width = width;
        this.height = height;

        this.isGone = isGone;

        this.doors = new ArrayList<>();
    }

    public int getStartX() {
        return isGone ? this.x : this.x + this.xOffset;
    }

    public int getEndX() {
        return isGone ? this.x + 26 : getStartX() + this.width - 1;
    }

    public int getStartY() {
        return isGone ? this.y: this.y + this.yOffset;
    }

    public int getEndY() {
        return isGone ? this.y + 8 : getStartY() + this.height - 1;
    }

    public void addDoor(@NotNull Door door) {
        door.setSymbol(isGone ? '$' : door.getSymbol());
        this.doors.add(door);
    }
}
