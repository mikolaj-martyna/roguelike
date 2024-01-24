package pl.umcs.entities;

import lombok.Builder;
import pl.umcs.items.chestplates.ClothArmor;
import pl.umcs.items.shoes.Feet;
import pl.umcs.items.weapons.Hand;

@Builder
public class Player extends Entity {
    public Player() {
        super();

        // GameElement
        setName("Player");
        setSymbol('@');
        setDescription("This is you. Go out there and begin your adventure!");

        // Entity
        setHealth(new Property(10));
        setAttack(new Property(4));
        setAgility(new Property(6));
        setDefense(new Property(5));
        setIntelligence(new Property(10));
        setCharisma(new Property(10));

        this.getEquipment().equipChestplate(new ClothArmor());
        this.getEquipment().equipShoes(new Feet());
        this.getEquipment().equipWeapon(new Hand());
    }
}
