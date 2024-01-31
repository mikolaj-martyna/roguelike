package pl.umcs.items.consumables;

public class HealthPotion extends Consumable {
    public HealthPotion() {
        this.setName("Health potion");
        this.setDescription("It heals you. :)");

        this.setHealth(2);
    }
}
