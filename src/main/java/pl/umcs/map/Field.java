package pl.umcs.map;

import lombok.*;

import pl.umcs.Entity;
import pl.umcs.GameElement;
import pl.umcs.Item;

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
