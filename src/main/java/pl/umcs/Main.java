package pl.umcs;

import pl.umcs.entities.Player;
import pl.umcs.entities.monsters.ThoughtEater;
import pl.umcs.items.special_items.EternalDynamo;
import pl.umcs.map.Field;
import pl.umcs.map.Map;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Misc */
        char[][] fields = {
            {
                '╔', '═', '═', '═', '═', '═', '═', '═', '═', '═', '╗', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '║', '░', '░', '░', '░', '░', '░', '░', '░', '░', '║', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '║', '░', '░', '░', '░', '░', '░', '░', '░', '░', '║', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '║', '░', '░', '░', '░', '░', '░', '░', '░', '░', '║', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '║', '░', '░', '░', '░', '░', '░', '░', '░', '░', '║', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '║', '░', '░', '░', '░', '╔', '═', '═', '═', '═', '╝', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '╚', '═', '#', '═', '═', '╝', ' ', ' ', ' ', ' ', ' ', '╔', '═', '═', '═', '═', '═',
                '╗'
            },
            {
                ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '║', '░', '░', '░', '░', '░',
                '║'
            },
            {
                ' ', ' ', '#', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#', '░', '░', '░', '░', '░',
                '║'
            },
            {
                ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', '║', '░', '░', '░', '░', '░',
                '║'
            },
            {
                ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', ' ', '╚', '═', '═', '═', '═', '═',
                '╝'
            }
        };

        /* Setup */
        // Input setup
        Scanner reader = new Scanner(System.in);

        // Output setup
        PrintWriter output = new PrintWriter(System.out, true, StandardCharsets.UTF_8);

        // Game setup
        Map map =
                Map.builder()
                        .level(new Field[11][18])
                        .rows(11)
                        .cols(18)
                        .entities(new ArrayList<>())
                        .items(new ArrayList<>())
                        .build();

        Player player = new Player();
        ThoughtEater thoughtEater = new ThoughtEater();

        EternalDynamo eternalDynamo = new EternalDynamo();
        EternalDynamo eternalDynamo2 = new EternalDynamo();

        map.load(fields);
        map.placeEntity(5, 3, player);
        map.placeEntity(8, 15, thoughtEater);
        map.placeItem(8, 16, eternalDynamo);
        map.placeItem(4, 2, eternalDynamo2);

        char input;
        /* Main loop */
        while (player.isAlive()) {
            // Take input
            input = reader.next().charAt(0);

            // Parse input
            //// WASD <- movement
            //// I <- inventory
            //// U <- equipment

            // TODO: handle walking into an enemy or a chest
            switch (input) {
                case 'w':
                    player.moveBy(map, -1, 0);
                    break;
                case 'a':
                    player.moveBy(map, 0, -1);
                    break;
                case 's':
                    player.moveBy(map, 1, 0);
                    break;
                case 'd':
                    player.moveBy(map, 0, 1);
                    break;
                case 'i':
                    while (input != 'q') {
                        // Show inventory
                        player.getEquipment().printEquipmentAndInventory(output);

                        // Get action
                        input = reader.next().charAt(0);

                        // Handle equipping the items
                        if (input == 'e') {
                            output.printf("Index of item to equip: ");

                            input = reader.next().charAt(0);
                            if (!player.getEquipment().getItems().isEmpty())
                                player.getEquipment()
                                        .equipItem(
                                                player.getEquipment()
                                                        .getItems()
                                                        .get(input - '0' - 1));
                        }
                    }

                    break;
            }

            map.print(output);
        }

        /* Cleanup */
        reader.close();
        output.close();
    }
}
