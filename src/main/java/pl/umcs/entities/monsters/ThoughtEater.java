package pl.umcs.entities.monsters;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Property;

// https://forgottenrealms.fandom.com/wiki/Thought_eater

public class ThoughtEater extends Entity {
    public ThoughtEater() {
        super();

        // GameElement
        setName("Thought eater");
        setSymbol('o');
        setDescription("Emaciated, sickly gray skeletal platypuses with over-sized heads.");

        // Entity
        setHealth(Property.builder().baseline(6).current(6).multiplier(1).build());
        setAttack(Property.builder().baseline(2).current(2).multiplier(1).build());
        setAgility(Property.builder().baseline(4).current(4).multiplier(1).build());
        setDefense(Property.builder().baseline(5).current(5).multiplier(1).build());
        setIntelligence(Property.builder().baseline(5).current(5).multiplier(1).build());
        setCharisma(Property.builder().baseline(4).current(4).multiplier(1).build());
    }
}
