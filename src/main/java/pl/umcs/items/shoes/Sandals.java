package pl.umcs.items.shoes;

public class Sandals extends Shoes {
    public Sandals() {
        super();

        this.setName("Sandals");
        this.setDescription("As once they served aristocrats, now they serve you.");

        this.setHealth(1);
        this.setAgility(6);
        this.setDefense(3);

        this.setAgilityMultiplier(1.3);
        this.setDefenseMultiplier(1.1);
    }
}
