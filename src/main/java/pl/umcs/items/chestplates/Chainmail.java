package pl.umcs.items.chestplates;

public class Chainmail extends Chestplate {
    public Chainmail() {
        super();

        this.setName("Chainmail");
        this.setDescription("Heavy, but protective.");

        this.setHealth(10);
        this.setAgility(-5);
        this.setDefense(7);

        this.setHealthMultiplier(1.2);
        this.setDefenseMultiplier(1.5);
    }
}
