package pl.umcs.entities.monsters;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Property;

public class ClockworkDragon extends Entity {
    public ClockworkDragon() {
        super();

        // GameElement
        setName("Clockwork Dragon");
        setSymbol('5');
        setDescription(
                "Towering monstrosity, combining intricate brass gears, whirring mechanisms, and bronze plating to form a majestic yet fearsome automaton resembling the mythical dragon.");

        // Entity
        setHealth(new Property(25));
        setAttack(new Property(10));
        setAgility(new Property(2));
        setDefense(new Property(30));
        setIntelligence(new Property(5));
        setCharisma(new Property(10));
    }
}
