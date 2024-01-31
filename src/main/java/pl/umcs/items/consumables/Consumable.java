package pl.umcs.items.consumables;

import pl.umcs.entities.Entity;
import pl.umcs.items.Item;

public class Consumable extends Item {
    public Consumable() {
        this.setSymbol('*');
    }

    public String getSlotName() {
        return "Consumable";
    }

    public void use(Entity entity) {}
}
