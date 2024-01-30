package pl.umcs.items.weapons;

public class ClockworkAxe extends Weapon {
    public ClockworkAxe() {
        super();

        this.setName("Clockwork axe");
        this.setDescription("You can hear the ticking. You can ignore it only for so long...");

        this.setAttack(7);
        this.setAttackMultiplier(1.75);
    }
}
