package pl.umcs.entities.monsters;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Property;

// https://forgottenrealms.fandom.com/wiki/Modron

public class Modron extends Entity {
    public Modron() {
        super();

        // GameElement
        setName("Modron");
        setSymbol('o');
        setDescription("Immortals known for their zealous adherence to the principles of law and order above all else.");

        // Entity
        setHealth(Property.builder().baseline(5).current(5).multiplier(1).build());
        setAttack(Property.builder().baseline(2).current(2).multiplier(1).build());
        setAgility(Property.builder().baseline(5).current(5).multiplier(1).build());
        setDefense(Property.builder().baseline(5).current(5).multiplier(1).build());
        setIntelligence(Property.builder().baseline(10).current(10).multiplier(1).build());
        setCharisma(Property.builder().baseline(7).current(7).multiplier(1).build());
    }
}
