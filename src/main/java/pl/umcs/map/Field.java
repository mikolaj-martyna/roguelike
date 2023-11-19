package pl.umcs.map;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.umcs.Entity;
import pl.umcs.GameElement;
import pl.umcs.Item;

import java.util.ArrayList;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
public class Field extends GameElement {
    Entity entity;
    ArrayList<Item> items;

    Field() {
        this.entity = null;
        this.items = new ArrayList<Item>();
    }
}
