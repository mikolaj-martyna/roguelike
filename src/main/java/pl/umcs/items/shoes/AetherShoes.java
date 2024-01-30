package pl.umcs.items.shoes;

public class AetherShoes extends Shoes {
    public AetherShoes() {
        super();

        this.setName("Aether shoes");
        this.setDescription("Mysterious, self propelling shoes taking energy from unknown source.");

        this.setHealth(3);
        this.setAgility(17);
        this.setDefense(6);

        this.setAgilityMultiplier(2);
        this.setDefenseMultiplier(1.25);
    }
}
