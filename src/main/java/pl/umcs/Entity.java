package pl.umcs;

import lombok.*;

import org.jetbrains.annotations.NotNull;
import pl.umcs.map.Map;

import java.util.ArrayList;
import java.util.Random;

@Builder
public class Entity extends GameElement {
    @Getter
    @Setter
    private int x;

    @Getter
    @Setter
    private int y;

    private ArrayList<Item> equipment;

    private Property health;
    private Property attack;
    private Property agility;
    private Property defense;
    private Property intelligence;
    private Property charisma;

    private Fraction fraction;

    // TODO: abilities

    // TODO: behavior
    public void heal(int amount) {
        this.health.current -= amount;
    }

    public void takeDamage(int damage) {
        this.health.current -= damage;
    }

    public int getBaselineHealth() {
        return this.health.baseline;
    }

    public int getHealth() {
        return this.health.current;
    }

    public boolean isAlive() {
        return this.health.current > 0;
    }

    public int attack(@NotNull Entity opponent) {
        if (opponent.agility.current >= new Random().nextInt()) {
            opponent.health.current -= this.attack.current;

            return this.attack.current;
        }

        return 0;
    }

    // TODO: movement
    public void moveBy(@NotNull Map map, int offsetX, int offsetY) {
        int newX = this.getX() + offsetX;
        int newY = this.getY() + offsetY;

        if (map.canPlaceEntity(newX, newY)) {
            map.removeEntity(this.getX(), this.getY(), this);
            map.placeEntity(newX, newY, this);
        }
    }
}
