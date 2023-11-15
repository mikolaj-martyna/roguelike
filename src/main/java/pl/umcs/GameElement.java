package pl.umcs;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GameElement {
    private String name;
    private char symbol;
    private String description;
}
