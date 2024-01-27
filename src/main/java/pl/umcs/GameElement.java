package pl.umcs;

import lombok.*;

@Getter
@Setter
public class GameElement {
    private String name;
    private char symbol;
    private String description;

    public GameElement() {
        this.name = "";
        this.symbol = ' ';
        this.description = "";
    }
}
