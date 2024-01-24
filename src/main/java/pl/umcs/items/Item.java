package pl.umcs.items;

import lombok.*;

import pl.umcs.GameElement;
import pl.umcs.entities.Property;

@Getter
@Setter
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

    public String getSlotName() {
        return "";
    }
}
