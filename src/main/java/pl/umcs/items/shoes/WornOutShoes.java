package pl.umcs.items.shoes;

public class WornOutShoes extends Shoes {
    public WornOutShoes() {
        super();

        this.setName("Worn out shoes");
        this.setSymbol('i');
        this.setDescription(
                "These have more holes in them than cheese. Better find something else.");

        this.setAgility(1);
        this.setDefense(2);

        this.setAgilityMultiplier(1.1);
        this.setDefenseMultiplier(1.1);
    }

    public WornOutShoes(boolean starter) {
        this();

        if (starter) {
            this.setAgility(0);
            this.setDefense(0);

            this.setAgilityMultiplier(1);
            this.setDefenseMultiplier(1);
        }
    }
}
