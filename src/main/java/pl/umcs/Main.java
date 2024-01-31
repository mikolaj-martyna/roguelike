package pl.umcs;

import pl.umcs.entities.Player;
import pl.umcs.entities.Entity;
import pl.umcs.map.Map;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;
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

        // Rounds
        Queue<Entity> roundOrder = new LinkedList<>();

        int currentGameLevel = 0;
        int round = 0;

        roundOrder.add(player);

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
            output.printf("Round: %d\nMove: WASD\tInventory: I\nAction: ", round);

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

        map.printSummary(output, player);

        /* Cleanup */
        reader.close();
        output.close();
    }
}
