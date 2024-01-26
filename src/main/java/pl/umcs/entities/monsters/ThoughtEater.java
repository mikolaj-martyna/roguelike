package pl.umcs.entities.monsters;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Player;
import pl.umcs.entities.Property;
import pl.umcs.map.Map;

import java.util.Random;

// https://forgottenrealms.fandom.com/wiki/Thought_eater

public class ThoughtEater extends Entity {
    public ThoughtEater() {
        super();

        // GameElement
        setName("Thought eater");
        setSymbol('o');
        setDescription("Emaciated, sickly gray skeletal platypuses with over-sized heads.");

        // Entity
        setHealth(new Property(3));
        setAttack(new Property(1));
        setAgility(new Property(4));
        setDefense(new Property(2));
        setIntelligence(new Property(1));
        setCharisma(new Property(4));
    }

    @Override
    public void move(Map map, Player player) {
        moveBy(map, new Random().nextInt(3) - 1, new Random().nextInt(3) - 1);
    }
}
