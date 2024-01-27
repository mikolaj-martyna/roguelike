package pl.umcs;

import pl.umcs.entities.Player;
import pl.umcs.entities.monsters.Inevitable;
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
        /* Setup */
        // Input
        Scanner reader = new Scanner(System.in, StandardCharsets.UTF_8);

        // Output
        PrintWriter output = new PrintWriter(System.out, true, StandardCharsets.UTF_8);

        // Game
        Map map = new Map();
        Player player = new Player();

        map.placeEntity(map.getCurrentLevel().getStartingX(), map.getCurrentLevel().getStartingY(), player);

        /* Main loop */
        while (player.isAlive()) {
            // Print current map state
            map.print(output);

            // Take input
            char input = reader.next().charAt(0);

            // Parse input
            //// WASD <- movement
            //// I <- inventory

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

                        if (input == 'e') {
                            // TODO: print equipment as pages with item indexes 1-9
                            output.printf("Index of item to equip: ");
                            input = reader.next().charAt(0);

                            // TODO: check if input is a number, if not print error message
                            if (!player.getEquipment().getItems().isEmpty() && validateNumber(input)) {
                                player.getEquipment()
                                        .equipItem(
                                                player.getEquipment()
                                                        .getItems()
                                                        .get(input - '1'));
                            }
                        }
                    }

                    break;
            }
        }

        output.printf("You died.");

        /* Cleanup */
        reader.close();
        output.close();
    }

    public static boolean validateNumber(char input) {
        return input >= '0' && input <= '9';
    }
}
