package pl.umcs.entities;

import lombok.*;

import org.jetbrains.annotations.NotNull;

import pl.umcs.GameElement;
import pl.umcs.Graph;
import pl.umcs.items.Equipment;
import pl.umcs.items.Item;
import pl.umcs.map.Map;
import pl.umcs.map.Passage;

import java.io.PrintWriter;
import java.util.*;

@Getter
@Setter
public class Entity extends GameElement {
    private Equipment equipment;

    private Property health;
    private Property attack;
    private Property agility;
    private Property defense;
    private Property intelligence;
    private Property charisma;

    private Fraction fraction;

    private int x;
    private int y;

    private int numberOfDice;
    private int maxRange;

    public Entity() {
        equipment = new Equipment();
    }

    /* Getters */
    public int getBaselineHealth() {
        return this.health.baseline;
    }

    public int getHealth() {
        return this.health.current;
    }

    public int getAttack() {
        return (int)
                ((this.attack.baseline * this.attack.multiplier)
                        + (this.getEquipment().getAttack()));
    }

    public int getAgility() {
        return (int)
                ((this.agility.baseline * this.agility.multiplier)
                        + (this.getEquipment().getAgility()));
    }

    public int getDefense() {
        return (int)
                ((this.defense.baseline * this.defense.multiplier)
                        + (this.getEquipment().getDefense()));
    }

    public int getIntelligence() {
        return (int)
                ((this.intelligence.baseline * this.intelligence.multiplier)
                        + (this.getEquipment().getIntelligence()));
    }

    public int getCharisma() {
        return (int)
                ((this.charisma.baseline * this.charisma.multiplier)
                        + (this.getEquipment().getCharisma()));
    }

    /* Behavior */
    public void heal(int amount) {
        this.health.current += amount;
    }

    public void takeDamage(int damage) {
        this.health.current -= damage;
    }

    public boolean isAlive() {
        return this.getHealth() > 0;
    }

    public void attack(@NotNull Entity opponent) {
        if (opponent.getAgility() >= new Random().nextInt(100)) {
            int damageDealt = getAttack();
            double defenseMultiplier = (1 - ((double) opponent.getDefense() / 100));
            int damageTaken = (int) (damageDealt * defenseMultiplier);

            opponent.takeDamage(damageTaken);
        }
    }

    /* Movement */
    public void performAction(@NotNull Map map, @NotNull Player player) {
        // Two types of actions
        // 1. Move
        // 2. Equip item, if any in the inventory (higher chance to equip better items)

        Random random = new Random();

        // Move
        moveEntity(map, player);

        // Equip item
        if (random.nextBoolean()) {
            List<Item> items = this.getEquipment().getItems();

            if (!items.isEmpty()) {
                this.getEquipment().equipItem(items.get(random.nextInt(items.size())));
            }
        }
    }

    public void moveEntity(@NotNull Map map, Player player) {
        List<Graph.Node> pathToPlayer = map.pathToPlayer(this, player);

        int offsetX;
        int offsetY;

        if (pathToPlayer != null && pathToPlayer.size() > 1 && pathToPlayer.size() < 15) {
            offsetX = pathToPlayer.get(1).getX() - this.getX();
            offsetY = pathToPlayer.get(1).getY() - this.getY();
        } else {
            boolean willMoveX = new Random().nextBoolean();
            int amount = new Random().nextInt(3) - 1;

            offsetX = willMoveX ? amount : 0;
            offsetY = willMoveX ? 0 : amount;
        }

        moveBy(map, offsetX, offsetY);
    }

    public void moveBy(@NotNull Map map, int offsetX, int offsetY) {
        int newX = map.validateX(this.getX() + offsetX);
        int newY = map.validateY(this.getY() + offsetY);

        if (map.getFields()[newX][newY] instanceof Passage) {
            map.nextLevel();
            return;
        }

        if (map.hasItem(newX, newY)) {
            List<Item> items = map.getItems(newX, newY);

            for (Item item : items) {
                equipment.addItem(item);
            }

            map.removeItems(newX, newY);
            return;
        }

        if (map.hasMonster(newX, newY)) {
            Entity opponent = map.getFields()[newX][newY].getEntity();

            // Monsters are not meant to fight each-other
            if (!(this instanceof Player || opponent instanceof Player)) return;

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

            return;
        }

        if (map.canPlaceEntity(newX, newY)) {
            map.removeEntity(this.getX(), this.getY(), this);
            map.placeEntity(newX, newY, this);
        }
    }

    public void printStatistics(@NotNull PrintWriter output) {
        output.printf(
                "\033[0;1mHP: %d\tATK: %d\tAGL: %d\tDEF: %d\tINT: %d\tCHA: %d\n\033[0m",
                this.getHealth(),
                this.getAttack(),
                this.getAgility(),
                this.getDefense(),
                this.getIntelligence(),
                this.getCharisma());
    }
}
