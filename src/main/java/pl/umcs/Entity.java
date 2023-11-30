package pl.umcs;

import lombok.*;

import java.util.ArrayList;

@Builder
public class Entity extends GameElement {
    private int x;
    private int y;

    private ArrayList<Item> equipment;

    private Property health;
    private Property attack;
    private Property strength;
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

    public int attack(Entity opponent) {
        if (opponent.agility.current >= new Random().nextInt()) {
            opponent.health.current -= this.attack.current;

            return this.attack.current();
        }

	return 0;
    }
}
