package pl.umcs.map;

import lombok.*;

import pl.umcs.GameElement;
import pl.umcs.entities.Entity;
import pl.umcs.items.Item;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class Field extends GameElement {
    Entity entity;
    ArrayList<Item> items;

    protected Field() {
        this.setSymbol(' ');
        this.entity = null;
        this.items = new ArrayList<>();
    }
}
