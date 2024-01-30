package pl.umcs.items.shoes;

public class WornOutShoes extends Shoes {
    public WornOutShoes() {
        super();

        this.setName("Worn out shoes");
        this.setSymbol('i');
        this.setDescription(
                "These have more holes in them than cheese. Better find something else.");
    }
}
