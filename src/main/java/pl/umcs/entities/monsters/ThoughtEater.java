package pl.umcs.entities.monsters;

import org.jetbrains.annotations.NotNull;

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
        setSymbol('1');
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
    public void moveEntity(@NotNull Map map, Player player) {
        boolean willMoveX = new Random().nextBoolean();
        int amount = new Random().nextInt(3) - 1;

        int offsetX = willMoveX ? amount : 0;
        int offsetY = willMoveX ? 0 : amount;

        moveBy(map, offsetX, offsetY);
    }
}
