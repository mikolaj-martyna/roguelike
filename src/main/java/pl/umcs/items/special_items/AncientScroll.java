package pl.umcs.items.special_items;

public class AncientScroll extends SpecialItem {
    public AncientScroll() {
        setName("Ancient scroll");
        setDescription(
                "An old, dusty and run-down reel hiding something only those before us understood.");

        this.setHealth(10);
        this.setAttack(3);
        this.setAgility(2);
        this.setDefense(3);
        this.setIntelligence(12);
        this.setCharisma(20);

        this.setAgilityMultiplier(1.5);
        this.setDefenseMultiplier(1.1);
        this.setIntelligenceMultiplier(3);
        this.setCharismaMultiplier(2.25);
    }
}
