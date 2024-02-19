package pl.umcs.entities;

import lombok.Builder;

import pl.umcs.map.Map;

import static pl.umcs.utils.Output.output;
import static pl.umcs.utils.Reader.reader;

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

    public void handleEquipment(Map map) {
        char input = ' ';

        while (input != 'q') {
            // Show inventory
            this.getEquipment().printEquipmentAndInventory(output);

            // Get action
            input = reader.next().charAt(0);

            switch (input) {
                case 'e':
                    equip();
                    break;
                case 'd':
                    drop(map);
                    break;
                case 'u':
                    use();
                    break;
            }
        }
    }

    void equip() {
        output.printf("Index of item to equip: ");
        output.flush();

        char input = reader.next().charAt(0);

        if (!this.getEquipment().getItems().isEmpty()) {
            this.getEquipment().equipItem(this.getEquipment().getItems().get(input - '1'));
        }
    }

    void drop(Map map) {
        output.printf("Index of item to drop: ");
        output.flush();

        char input = reader.next().charAt(0);

        if (!this.getEquipment().getItems().isEmpty()) {
            this.getEquipment()
                    .dropItem(
                            map,
                            this.getEquipment().getItems().get(input - '1'),
                            this.getX(),
                            this.getY());
        }
    }

    void use() {
        output.printf("Index of item to use: ");
        output.flush();

        char input = reader.next().charAt(0);

        if (!this.getEquipment().getItems().isEmpty()) {
            this.getEquipment().useItem(this, this.getEquipment().getItems().get(input - '1'));
        }
    }
}
