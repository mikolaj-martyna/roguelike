package pl.umcs.items.chestplates;

public class ClothArmor extends Chestplate {
    public ClothArmor() {
        super();

        this.setName("Cloth armor");
        this.setSymbol('i');
        this.setDescription(
                "The only kind of wardrobe you have left after the collision. Will do for now.");

        this.setHealth(4);
        this.setDefense(4);

        this.setHealthMultiplier(1.1);
        this.setDefenseMultiplier(1.2);
    }


    public ClothArmor(boolean starter) {
        this();

        if (starter) {
            this.setHealth(0);
            this.setDefense(0);

            this.setHealthMultiplier(1);
            this.setDefenseMultiplier(1);
        }
    }
}
