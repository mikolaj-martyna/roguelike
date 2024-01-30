package pl.umcs.entities;

import lombok.Builder;

import org.jetbrains.annotations.NotNull;

import pl.umcs.map.Map;

import java.io.PrintWriter;
import java.util.Scanner;

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
    }

    public void handleEquipment(Map map, @NotNull Scanner reader, PrintWriter output) {
        char input = ' ';

        while (input != 'q') {
            // Show inventory
            this.getEquipment().printEquipmentAndInventory(output);

            // Get action
            input = reader.next().charAt(0);

            if (input == 'e') {
                output.printf("Index of item to equip: ");
                input = reader.next().charAt(0);

                if (!this.getEquipment().getItems().isEmpty()) {
                    this.getEquipment().equipItem(this.getEquipment().getItems().get(input - '1'));
                }
            } else if (input == 'd') {
                output.printf("Index of item to drop: ");
                input = reader.next().charAt(0);

                if (!this.getEquipment().getItems().isEmpty()) {
                    this.getEquipment()
                            .dropItem(
                                    map,
                                    this.getEquipment().getItems().get(input - '1'),
                                    this.getX(),
                                    this.getY());
                }
            }
        }
    }
}
