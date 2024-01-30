package pl.umcs.items.helms;

public class HelmetOfEnlightenment extends Helm {
    public HelmetOfEnlightenment() {
        super();

        this.setName("Helmet of enlightenment");
        this.setDescription("The purest form of knowledge, ready to be worn.");

        this.setHealth(-10);
        this.setDefense(-4);
        this.setIntelligence(17);

        this.setIntelligenceMultiplier(1.75);
    }
}
