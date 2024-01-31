package pl.umcs.entities.monsters;

import org.jetbrains.annotations.NotNull;
import pl.umcs.Graph;
import pl.umcs.entities.Entity;
import pl.umcs.entities.Player;
import pl.umcs.entities.Property;
import pl.umcs.map.Map;

import java.util.List;
import java.util.Random;

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

    @Override
    public void moveEntity(@NotNull Map map, Player player) {
        List<Graph.Node> pathToPlayer = map.pathToPlayer(this, player);

        if (pathToPlayer != null && pathToPlayer.size() > 1) {
            int offsetX = pathToPlayer.get(1).getX() - this.getX();
            int offsetY = pathToPlayer.get(1).getY() - this.getY();

            moveBy(map, offsetX, offsetY);
        }
    }
}
