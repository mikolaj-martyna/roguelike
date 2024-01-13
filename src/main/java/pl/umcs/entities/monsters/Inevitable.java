package pl.umcs.entities.monsters;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Property;

// https://forgottenrealms.fandom.com/wiki/Inevitable

public class Inevitable extends Entity {
    public Inevitable() {
        super();

        // GameElement
        setName("Inevitable");
        setSymbol('o');
        setDescription(
                "Lawful, intelligent constructs whose single purpose is to enforce the fundamental laws of nature and morality and punish those who violate them.");

        // Entity
        setHealth(Property.builder().baseline(8).current(8).multiplier(1).build());
        setAttack(Property.builder().baseline(6).current(6).multiplier(1).build());
        setAgility(Property.builder().baseline(5).current(5).multiplier(1).build());
        setDefense(Property.builder().baseline(8).current(8).multiplier(1).build());
        setIntelligence(Property.builder().baseline(8).current(8).multiplier(1).build());
        setCharisma(Property.builder().baseline(6).current(6).multiplier(1).build());
    }
}
