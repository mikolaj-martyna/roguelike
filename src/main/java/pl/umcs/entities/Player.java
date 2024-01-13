package pl.umcs.entities;

import lombok.Builder;

@Builder
public class Player extends Entity {
    Player() {
        super();

        // GameElement
        setName("Player");
        setSymbol('@');
        setDescription("This is you. Go out there and begin your adventure!");

        // Entity
        setHealth(Property.builder().baseline(10).current(10).multiplier(1).build());
        setAttack(Property.builder().baseline(10).current(10).multiplier(1).build());
        setAgility(Property.builder().baseline(10).current(10).multiplier(1).build());
        setDefense(Property.builder().baseline(10).current(10).multiplier(1).build());
        setIntelligence(Property.builder().baseline(10).current(10).multiplier(1).build());
        setCharisma(Property.builder().baseline(10).current(10).multiplier(1).build());
    }
}
