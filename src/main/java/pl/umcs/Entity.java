package pl.umcs;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Entity extends GameElement {
    int x;
    int y;

    Item[] equipment;

    Property health;
    Property attack;
    Property strength;
    Property agility;
    Property defense;
    Property intelligence;
    Property charisma;

    Fraction fraction;

    // TODO: abilities

    // TODO: behavior
}
