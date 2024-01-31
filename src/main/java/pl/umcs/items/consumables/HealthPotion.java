package pl.umcs.items.consumables;

import org.jetbrains.annotations.NotNull;
import pl.umcs.entities.Entity;

public class HealthPotion extends Consumable {
    public HealthPotion() {
        this.setName("Health potion");
        this.setDescription("It heals you. :)");

        this.setHealth(2);
    }

    @Override
    public void use(@NotNull Entity entity) {
        entity.heal(this.getHealth());
    }
}
