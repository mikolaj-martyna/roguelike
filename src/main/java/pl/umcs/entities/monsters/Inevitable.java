package pl.umcs.entities.monsters;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Property;

// https://forgottenrealms.fandom.com/wiki/Inevitable

public class Inevitable extends Entity {
    public Inevitable() {
        super();

        // GameElement
        setName("Inevitable");
        setSymbol('3');
        setDescription(
                "Lawful, intelligent constructs whose single purpose is to enforce the fundamental laws of nature and morality and punish those who violate them.");

        // Entity
        setHealth(new Property(10));
        setAttack(new Property(5));
        setAgility(new Property(10));
        setDefense(new Property(5));
        setIntelligence(new Property(8));
        setCharisma(new Property(6));
    }
}
