package pl.umcs;

import pl.umcs.entities.Player;
import pl.umcs.items.special_items.EternalDynamo;
import pl.umcs.map.Map;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Setup */
        // Input
        Scanner reader = new Scanner(System.in, StandardCharsets.UTF_8);

        // Output
        PrintWriter output = new PrintWriter(System.out, true, StandardCharsets.UTF_8);

        // Game
        Map map = new Map(20);
        Player player = new Player();

        map.placeEntity(map.getCurrentStartingX(), map.getCurrentStartingY(), player);

        int currentGameLevel = 0;
        int round = 0;

        /* Main loop */
        while (player.isAlive()) {
            round++;

            if (currentGameLevel != map.getCurrentLevelNumber()) {
                if (++currentGameLevel == map.getLevelsAmount()) break;

                map.placeEntity(map.getCurrentStartingX(), map.getCurrentStartingY(), player);
            }

            // Print current map state
            map.print(output);
            player.printStatistics(output);
            output.printf("Round: %d\nInventory: I\tMove: WASD\nAction: ", round);

            // Take input
            char input = reader.next().charAt(0);

            // Parse input
            //// WASD <- movement
            //// I <- inventory

            switch (input) {
                case 'w':
                    player.moveBy(map, 0, -1);
                    break;
                case 'a':
                    player.moveBy(map, -1, 0);
                    break;
                case 's':
                    player.moveBy(map, 0, 1);
                    break;
                case 'd':
                    player.moveBy(map, 1, 0);
                    break;
                case 'i':
                    player.handleEquipment(map, reader, output);
                    break;
            }
        }

        if (!player.isAlive()) output.printf("You died.");
        if (player.getEquipment().getSpecialItem() instanceof EternalDynamo
                || player.getEquipment().getItems().stream().anyMatch(item -> item instanceof EternalDynamo))
            output.printf(
                    "You've found the Eternal Dynamo! You can head back to your island now. Rest now, you did well.");
        else
            output.printf(
                    "You've failed this time, but worry not! You can search again whenever you like.");

        /* Cleanup */
        reader.close();
        output.close();
    }
}
