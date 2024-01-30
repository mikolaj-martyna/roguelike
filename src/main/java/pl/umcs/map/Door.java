package pl.umcs.map;

public class Door extends Field {
    int x;
    int y;

    public Door() {
        this(0, 0);
    }

    public Door(int x, int y) {
        this.setName("door");
        this.setSymbol('+');
        this.setDescription(
                "Door to the room. Nothing special, but may be hard to open sometimes.");

        this.x = x;
        this.y = y;
    }
}
