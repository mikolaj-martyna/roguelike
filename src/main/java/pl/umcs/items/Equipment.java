package pl.umcs.items;

import lombok.Getter;

import org.jetbrains.annotations.NotNull;

import pl.umcs.items.chestplates.Chestplate;
import pl.umcs.items.helms.Helm;
import pl.umcs.items.shoes.Shoes;
import pl.umcs.items.special_items.SpecialItem;
import pl.umcs.items.weapons.Weapon;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Equipment {
    @Getter List<Item> items;
    private Helm helm;
    private Chestplate chestplate;
    private Shoes shoes;
    private Weapon weapon;
    private SpecialItem specialItem;

    public Equipment() {
        this.helm = null;
        this.chestplate = null;
        this.shoes = null;

        this.weapon = null;
        this.specialItem = null;

        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void equipItem(Item item) {
        if (item instanceof Helm) equipHelm((Helm) item);
        if (item instanceof Chestplate) equipChestplate((Chestplate) item);
        if (item instanceof Shoes) equipShoes((Shoes) item);
        if (item instanceof Weapon) equipWeapon((Weapon) item);
        if (item instanceof SpecialItem) equipSpecialItem((SpecialItem) item);
    }

    public void equipHelm(@NotNull Helm helm) {
        if (this.helm != null) unequipHelm();

        this.helm = helm;
        this.items.remove(helm);
    }

    public void equipChestplate(@NotNull Chestplate chestplate) {
        if (this.chestplate != null) unequipChestplate();

        this.chestplate = chestplate;
        this.items.remove(chestplate);
    }

    public void equipShoes(@NotNull Shoes shoes) {
        if (this.shoes != null) unequipShoes();

        this.shoes = shoes;
        this.items.remove(shoes);
    }

    public void equipWeapon(@NotNull Weapon weapon) {
        if (this.weapon != null) unequipWeapon();

        this.weapon = weapon;
        this.items.remove(weapon);
    }

    public void equipSpecialItem(@NotNull SpecialItem specialItem) {
        if (this.specialItem != null) unequipSpecialItem();

        this.specialItem = specialItem;
        this.items.remove(specialItem);
    }

    public void unequipHelm() {
        this.items.add(this.helm);
        this.helm = null;
    }

    public void unequipChestplate() {
        this.items.add(this.chestplate);
        this.chestplate = null;
    }

    public void unequipShoes() {
        this.items.add(this.shoes);
        this.shoes = null;
    }

    public void unequipWeapon() {
        this.items.add(this.weapon);
        this.weapon = null;
    }

    public void unequipSpecialItem() {
        this.items.add(this.specialItem);
        this.specialItem = null;
    }

    public void printEquipmentAndInventory(@NotNull PrintWriter output) {
        output.print("\033[H\033[2J");
        output.flush();

        printEquipment(output);
        printItems(output);

        output.printf("\nquit: q\tequip: e\n");
    }

    public void printEquipment(@NotNull PrintWriter output) {
        output.printf(
                """
                \033[1mEquipment\033[0m
    
                Head: %s
                Body: %s
                Feet: %s
                Hands: %s
                Special Item: %s
    
                """,
                this.helm != null ? this.helm.getName() : "None",
                this.chestplate.getName(),
                this.shoes.getName(),
                this.weapon.getName(),
                this.specialItem != null ? this.specialItem.getName() : "None");
    }

    public void printItems(@NotNull PrintWriter output) {
        output.printf("\033[1mInventory\033[0m\n");
        int index = 1;
        for (Item item : items) {
            output.printf("%d. [%s] %s (%s)\n", index++, item.getSlotName(), item.getName(), item);
        }
    }
}
