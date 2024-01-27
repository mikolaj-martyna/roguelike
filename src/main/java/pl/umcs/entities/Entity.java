package pl.umcs.entities;

import lombok.*;

import org.jetbrains.annotations.NotNull;

import pl.umcs.GameElement;
import pl.umcs.items.Equipment;
import pl.umcs.items.Item;
import pl.umcs.map.Map;

import java.util.*;

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
        this.health.current += amount;
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
        return this.getHealth() > 0;
    }

    public void attack(@NotNull Entity opponent) {
        if (opponent.agility.current >= new Random().nextInt(100)) {
            int damageDealt = (int) (this.attack.current * this.attack.multiplier);
            double defenseMultiplier =
                    (1 - (((double) opponent.defense.current / 100) * opponent.defense.multiplier));
            int damageTaken = (int) (damageDealt * defenseMultiplier);

            opponent.takeDamage(damageTaken);
        }
    }

    // Movement
    public void moveBy(@NotNull Map map, int offsetX, int offsetY) {
        int newX = this.getX() + offsetX;
        int newY = this.getY() + offsetY;

        if (map.hasItem(newX, newY)) {
            List<Item> items = map.getItems(newX, newY);

            for (Item item : items) {
                equipment.addItem(item);
            }

            map.removeItems(newX, newY);
        }

        if (map.hasMonster(newX, newY)) {
            Entity opponent = map.getFields()[newX][newY].getEntity();

            // TODO: improve attack mechanic (user input during combat)
            while (this.isAlive() && opponent.isAlive()) {
                this.attack(opponent);

                if (opponent.isAlive()) {
                    opponent.attack(this);
                }
            }

            // Opponent killed
            if (this.isAlive()) {
                map.removeEntity(opponent);
            }

            // Attacker killed
            if (opponent.isAlive()) {
                map.removeEntity(this);
            }
        }

        if (map.canPlaceEntity(newX, newY)) {
            map.removeEntity(this.getX(), this.getY(), this);
            map.placeEntity(newX, newY, this);
        }
    }

    public void move(Map map, Player player) {
        if (Map.distance(this, player) < 10) {
            // TODO: change distance calculation to DFS
            // TODO: add next step direction
        } else {
            moveBy(map, new Random().nextInt(3) - 1, new Random().nextInt(3) - 1);
        }
    }
}
