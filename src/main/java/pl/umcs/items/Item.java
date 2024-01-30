package pl.umcs.items;

import lombok.*;

import pl.umcs.GameElement;

@Setter
@Getter
public class Item extends GameElement {
    private int health;
    private int attack;
    private int agility;
    private int defense;
    private int intelligence;
    private int charisma;

    private double healthMultiplier;
    private double attackMultiplier;
    private double agilityMultiplier;
    private double defenseMultiplier;
    private double intelligenceMultiplier;
    private double charismaMultiplier;

    public Item() {
        this.setSymbol('i');

        this.health = 0;
        this.attack = 0;
        this.agility = 0;
        this.defense = 0;
        this.intelligence = 0;
        this.charisma = 0;

        this.healthMultiplier = 1;
        this.attackMultiplier = 1;
        this.agilityMultiplier = 1;
        this.defenseMultiplier = 1;
        this.intelligenceMultiplier = 1;
        this.charismaMultiplier = 1;
    }

    /* Getters */
    public int getHealth() {
        return (int) (this.health * this.healthMultiplier);
    }

    public int getAttack() {
        return (int) (this.attack * this.attackMultiplier);
    }

    public int getAgility() {
        return (int) (this.agility * this.agilityMultiplier);
    }

    public int getDefense() {
        return (int) (this.defense * this.defenseMultiplier);
    }

    public int getIntelligence() {
        return (int) (this.intelligence * this.intelligenceMultiplier);
    }

    public int getCharisma() {
        return (int) (this.charisma * this.charismaMultiplier);
    }

    public String getSlotName() {
        return "";
    }

    @Override
    public String toString() {
        return "HP: +"
                + this.getHealth()
                + " ATK: +"
                + this.getAttack()
                + "AGL: +"
                + this.getAgility()
                + " DEF: +"
                + this.getDefense();
    }
}
