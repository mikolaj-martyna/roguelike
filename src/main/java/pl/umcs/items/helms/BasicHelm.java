package pl.umcs.items.helms;

public class BasicHelm extends Helm {
    public BasicHelm() {
        super();

        this.setName("Basic helm");
        this.setDescription("Nothing fancy, but good enough to prevent some minor damage.");

        this.setHealth(2);
        this.setDefense(2);
        this.setIntelligence(3);

        this.setIntelligenceMultiplier(1.1);
    }

    public BasicHelm(boolean starter) {
        this();

        if (starter) {
            this.setHealth(0);
            this.setDefense(0);
            this.setIntelligence(0);

            this.setIntelligenceMultiplier(1);
        }
    }
}
