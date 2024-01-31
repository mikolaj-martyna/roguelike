package pl.umcs.items.consumables;

import pl.umcs.items.Item;

public class Consumable extends Item {
    public Consumable() {
        this.setSymbol('*');
    }

    public String getSlotName() {
        return "Consumable";
    }
}
