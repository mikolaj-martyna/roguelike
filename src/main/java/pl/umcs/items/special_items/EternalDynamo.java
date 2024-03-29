package pl.umcs.items.special_items;

public class EternalDynamo extends SpecialItem {
    public EternalDynamo() {
        setName("Eternal Dynamo");
        setSymbol('D');
        setDescription("This is it. Everything we came here for, right before our eyes.");

        this.setHealth(30);
        this.setAttack(10);
        this.setAgility(15);
        this.setDefense(25);

        this.setAttackMultiplier(2);
    }
}
