package pl.umcs.items.weapons;

public class Stick extends Weapon {
    public Stick() {
        super();

        this.setName("Stick");
        this.setDescription("The most basic of weapons, but still deadly if used right.");

        this.setAttack(2);
        this.setAgility(1);

        this.setAttackMultiplier(1.1);
        this.setAgilityMultiplier(1.1);
    }

    public Stick(boolean starter) {
        this();

        if (starter) {
            this.setAttack(0);
            this.setAgility(0);

            this.setAttackMultiplier(1);
            this.setAgilityMultiplier(1);
        }
    }
}
