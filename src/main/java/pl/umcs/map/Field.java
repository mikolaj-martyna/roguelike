package pl.umcs.map;

import lombok.*;

import pl.umcs.GameElement;
import pl.umcs.items.Item;
import pl.umcs.entities.Entity;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class Field extends GameElement {
    Entity entity;
    ArrayList<Item> items;

    Field() {
        this.setSymbol(' ');
        this.entity = null;
        this.items = new ArrayList<>();
    }
}
