package pl.umcs.entities.monsters;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Property;

public class ClockworkDragon extends Entity {
    public ClockworkDragon() {
        super();

        // GameElement
        setName("Clockwork Dragon");
        setSymbol('o');
        setDescription("Towering monstrosity, combining intricate brass gears, whirring mechanisms, and bronze plating to form a majestic yet fearsome automaton resembling the mythical dragon.");

        // Entity
        setHealth(Property.builder().baseline(16).current(16).multiplier(1).build());
        setAttack(Property.builder().baseline(10).current(10).multiplier(1).build());
        setAgility(Property.builder().baseline(4).current(4).multiplier(1).build());
        setDefense(Property.builder().baseline(12).current(12).multiplier(1).build());
        setIntelligence(Property.builder().baseline(6).current(6).multiplier(1).build());
        setCharisma(Property.builder().baseline(6).current(6).multiplier(1).build());
    }
}
