package pl.umcs.items.special_items;

public class Feather extends SpecialItem {
    public Feather() {
        setName("Feather");
        setDescription("Not very useful, but feels nice in the hand.");

        this.setAgility(1);
        this.setIntelligence(3);
        this.setCharisma(2);

        this.setAgilityMultiplier(1.1);
        this.setIntelligenceMultiplier(1.1);
        this.setCharismaMultiplier(1.1);
    }

    public Feather(boolean starter) {
        this();

        if (starter) {
            this.setAgility(0);
            this.setIntelligence(0);
            this.setCharisma(0);

            this.setAgilityMultiplier(1);
            this.setIntelligenceMultiplier(1);
            this.setCharismaMultiplier(1);
        }
    }
}
