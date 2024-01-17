package pl.umcs.entities.monsters;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Property;

public class Myr extends Entity {
    public Myr() {
        super();

        // GameElement
        setName("Myr");
        setSymbol('o');
        setDescription("Small construct.");

        // Entity
        setHealth(new Property(7));
        setAttack(new Property(3));
        setAgility(new Property(7));
        setDefense(new Property(3));
        setIntelligence(new Property(3));
        setCharisma(new Property(4));
    }
}
