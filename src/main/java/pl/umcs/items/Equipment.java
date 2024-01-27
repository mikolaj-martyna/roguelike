package pl.umcs.items;

import lombok.Getter;

import org.jetbrains.annotations.NotNull;

import pl.umcs.items.chestplates.Chestplate;
import pl.umcs.items.helms.Helm;
import pl.umcs.items.shoes.Shoes;
import pl.umcs.items.special_items.SpecialItem;
import pl.umcs.items.weapons.Weapon;

import javax.crypto.ShortBufferException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Equipment {
    List<Item> items;

    private Helm helm;
    private Chestplate chestplate;
    private Shoes shoes;
    private Weapon weapon;
    private SpecialItem specialItem;

    public Equipment() {
        this.helm = new Helm();
        this.chestplate = new Chestplate();
        this.shoes = new Shoes();

        this.weapon = new Weapon();
        this.specialItem = new SpecialItem();

        items = new ArrayList<>();
    }

    /* Inventory management */
    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    /* Equipment management */
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

    /* Getters */
    // Combined item statistics
    public int getHealth() {
        return this.helm.getHealth()
                + this.chestplate.getHealth()
                + this.shoes.getHealth()
                + this.specialItem.getHealth()
                + this.weapon.getHealth();
    }

    public int getAttack() {
        return this.helm.getAttack()
                + this.chestplate.getAttack()
                + this.shoes.getAttack()
                + this.specialItem.getAttack()
                + this.weapon.getAttack();
    }

    public int getAgility() {
        return this.helm.getAgility()
                + this.chestplate.getAgility()
                + this.shoes.getAgility()
                + this.specialItem.getAgility()
                + this.weapon.getAgility();
    }

    public int getDefense() {
        return this.helm.getDefense()
                + this.chestplate.getDefense()
                + this.shoes.getDefense()
                + this.specialItem.getDefense()
                + this.weapon.getDefense();
    }

    public int getIntelligence() {
        return this.helm.getIntelligence()
                + this.chestplate.getIntelligence()
                + this.shoes.getIntelligence()
                + this.specialItem.getIntelligence()
                + this.weapon.getIntelligence();
    }

    public int getCharisma() {
        return this.helm.getCharisma()
                + this.chestplate.getCharisma()
                + this.shoes.getCharisma()
                + this.specialItem.getCharisma()
                + this.weapon.getCharisma();
    }

    /* Printing */
    public void printEquipmentAndInventory(@NotNull PrintWriter output) {
        output.print("\033[H\033[2J");
        output.flush();

        printEquipment(output);
        printItems(output);
    }

    public void printEquipment(@NotNull PrintWriter output) {
        output.printf(
                """
                \033[1mEquipment\033[0m

                Helm: %s
                Chestplate: %s
                Shoes: %s
                Weapon: %s
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
