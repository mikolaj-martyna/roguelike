package pl.umcs.items;

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
    List<Item> items;
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

    public void equipHelm(@NotNull Helm helm) {
        this.helm = helm;
        this.items.remove(helm);
    }

    public void equipChestplate(@NotNull Chestplate chestplate) {
        this.chestplate = chestplate;
        this.items.remove(chestplate);
    }

    public void equipShoes(@NotNull Shoes shoes) {
        this.shoes = shoes;
        this.items.remove(shoes);
    }

    public void equipWeapon(@NotNull Weapon weapon) {
        this.weapon = weapon;
        this.items.remove(weapon);
    }

    public void equipSpecialItem(@NotNull SpecialItem specialItem) {
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

    public void printItems(@NotNull PrintWriter output) {
        output.print("\033[H\033[2J");
        output.flush();

        int index = 1;
        for (Item item : items) {
            output.printf("%d. %s\n", index++, item.getName());
        }
    }
}
