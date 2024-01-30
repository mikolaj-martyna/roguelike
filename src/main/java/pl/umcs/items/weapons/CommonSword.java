package pl.umcs.items.weapons;

public class CommonSword extends Weapon {
    public CommonSword() {
        super();

        this.setName("Common sword");
        this.setDescription("This weapon is tried and tested, but won't suffice forever.");

        this.setAttack(3);
        this.setAttackMultiplier(1.25);
    }
}
