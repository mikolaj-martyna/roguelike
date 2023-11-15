package pl.umcs;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Field extends GameElement {
    Entity entity;
    ArrayList<Item> items;
}
