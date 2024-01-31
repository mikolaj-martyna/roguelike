package pl.umcs.items;

import lombok.Getter;

import org.jetbrains.annotations.NotNull;

import pl.umcs.entities.Entity;
import pl.umcs.items.chestplates.Chestplate;
import pl.umcs.items.chestplates.ClothArmor;
import pl.umcs.items.consumables.Consumable;
import pl.umcs.items.helms.BasicHelm;
import pl.umcs.items.helms.Helm;
import pl.umcs.items.shoes.Shoes;
import pl.umcs.items.shoes.WornOutShoes;
import pl.umcs.items.special_items.Feather;
import pl.umcs.items.special_items.SpecialItem;
import pl.umcs.items.weapons.Stick;
import pl.umcs.items.weapons.Weapon;
import pl.umcs.map.Map;

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
        this.helm = new BasicHelm(true);
        this.chestplate = new ClothArmor(true);
        this.shoes = new WornOutShoes(true);

        this.weapon = new Stick(true);
        this.specialItem = new Feather(true);

        items = new ArrayList<>();
    }

    /* Inventory management */
    public void addItem(Item item) {
        if (items.size() < 9) items.add(item);
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
        this.addItem(this.helm);
        this.helm = null;
    }

    public void unequipChestplate() {
        this.addItem(this.chestplate);
        this.chestplate = null;
    }

    public void unequipShoes() {
        this.addItem(this.shoes);
        this.shoes = null;
    }

    public void unequipWeapon() {
        this.addItem(this.weapon);
        this.weapon = null;
    }

    public void unequipSpecialItem() {
        this.addItem(this.specialItem);
        this.specialItem = null;
    }

    public void dropItem(@NotNull Map map, Item item, int x, int y) {
        map.getCurrentLevel().getItems().add(item);
        map.getCurrentLevel().getFields()[x][y].getItems().add(item);

        this.removeItem(item);
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

        output.printf("Equip: e  Use: u  Drop: d  Quit: q\n");
        output.flush();
    }

    public void printEquipment(@NotNull PrintWriter output) {
        output.printf(
                """
                \033[1mEquipment\033[0m

                \033[1mHelm:\033[0m %s
                \033[1mChestplate:\033[0m %s
                \033[1mShoes:\033[0m %s
                \033[1mWeapon:\033[0m %s
                \033[1mSpecial item:\033[0m %s

                """,
                this.helm != null ? this.helm : "None",
                this.specialItem != null ? this.chestplate : "None",
                this.specialItem != null ? this.shoes : "None",
                this.specialItem != null ? this.weapon : "None",
                this.specialItem != null ? this.specialItem : "None");
    }

    public void printItems(@NotNull PrintWriter output) {
        output.printf("\033[1mInventory\033[0m\n");

        int index = 1;
        if (items.isEmpty()) {
            output.printf("Empty\n");
        } else {
            for (Item item : items) {
                output.printf("%d. [%s] %s\n", index++, item.getSlotName(), item);
            }
        }
    }

    public void useItem(Entity entity, Item item) {
        if (item instanceof Consumable) {
            ((Consumable) item).use(entity);
            removeItem(item);
        }
    }
}
