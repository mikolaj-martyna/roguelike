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
        setHealth(new Property(18));
        setAttack(new Property(10));
        setAgility(new Property(15));
        setDefense(new Property(20));
        setIntelligence(new Property(15));
        setCharisma(new Property(15));
    }
}
