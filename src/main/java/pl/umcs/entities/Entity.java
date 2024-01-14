package pl.umcs.entities;

import lombok.*;

import org.jetbrains.annotations.NotNull;

import pl.umcs.GameElement;
import pl.umcs.items.Equipment;
import pl.umcs.items.Item;
import pl.umcs.map.Map;

import java.util.ArrayList;
import java.util.Random;

@Getter
@Setter
public class Entity extends GameElement {
    private int x;
    private int y;

    private Equipment equipment;

    private Property health;
    private Property attack;
    private Property agility;
    private Property defense;
    private Property intelligence;
    private Property charisma;

    private Fraction fraction;

    private int numberOfDice;
    private int maxRange;

    public Entity() {
        this.x = -1;
        this.y = -1;

        equipment = new Equipment();
    }

    // Behavior
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
        if (opponent.agility.current >= new Random().nextInt(100)) {
            int damageDealt = (int) (this.attack.current * this.attack.multiplier);
            int defenseMultiplier =
                    (int) (1 - ((opponent.defense.current / 100) * opponent.defense.multiplier));
            int damageTaken = damageDealt * defenseMultiplier;

            opponent.health.current -= damageTaken;

            return damageTaken;
        }

        return 0;
    }

    // Movement
    public void moveBy(@NotNull Map map, int offsetX, int offsetY) {
        int newX = this.getX() + offsetX;
        int newY = this.getY() + offsetY;

        if (map.canPlaceEntity(newX, newY)) {
            map.removeEntity(this.getX(), this.getY(), this);
            map.placeEntity(newX, newY, this);
        }
    }
}
