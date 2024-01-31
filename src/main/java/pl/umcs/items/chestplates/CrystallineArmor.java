package pl.umcs.items.chestplates;

public class CrystallineArmor extends Chestplate {
    public CrystallineArmor() {
        super();

        this.setName("Crystalline armor");
        this.setDescription("Looks astonishing and weights next to nothing.");

        this.setHealth(15);
        this.setDefense(20);

        this.setHealthMultiplier(1.5);
        this.setDefenseMultiplier(2);
    }
}
