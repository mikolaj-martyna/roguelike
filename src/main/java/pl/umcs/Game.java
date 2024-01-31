package pl.umcs;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Player;
import pl.umcs.map.Map;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        /* Setup */
        // Input
        Scanner reader = new Scanner(System.in, StandardCharsets.UTF_8);

        // Output
        PrintWriter output = new PrintWriter(System.out, false, StandardCharsets.UTF_8);

        // Game
        Map map = new Map(20);
        Player player = new Player();

        map.placeEntity(map.getCurrentStartingX(), map.getCurrentStartingY(), player);

        // Rounds
        Queue<Entity> roundOrder = new LinkedList<>();
        roundOrder.add(player);

        int currentGameLevel = -1;
        int round = 0;

        /* Main loop */
        while (player.isAlive()) {
            if (currentGameLevel != map.getCurrentLevelNumber()) {
                if (++currentGameLevel == map.getLevelsAmount()) break;

                roundOrder.clear();

                roundOrder.add(player);
                roundOrder.addAll(map.getEntities().stream().filter(e -> !(e instanceof Player)).toList());

                map.placeEntity(map.getCurrentStartingX(), map.getCurrentStartingY(), player);
            }

            Entity currentEntity = roundOrder.poll();

            if (currentEntity instanceof Player) {
                round++;

                // Print current map state
                output.print("\033[H\033[2J");
                output.flush();

                map.print(output);

                player.printStatistics(output);

                output.printf("Round: %d\nMove: WASD  Inventory: I\nAction: ", round);
                output.flush();

                // Take input
                char input = reader.next().charAt(0);

                // Parse input
                //// WASD <- movement
                //// I <- inventory

                switch (input) {
                    case 'w':
                        currentEntity.moveBy(map, 0, -1);
                        break;
                    case 'a':
                        currentEntity.moveBy(map, -1, 0);
                        break;
                    case 's':
                        currentEntity.moveBy(map, 0, 1);
                        break;
                    case 'd':
                        currentEntity.moveBy(map, 1, 0);
                        break;
                    case 'i':
                        ((Player) currentEntity).handleEquipment(map, reader, output);
                        break;
                }

                roundOrder.add(currentEntity);
            } else if (currentEntity != null && currentEntity.isAlive()) {
                currentEntity.performAction(map, player);

                if (currentEntity.isAlive()) roundOrder.add(currentEntity);
            }
        }

        map.printSummary(output, player);

        /* Cleanup */
        reader.close();
        output.close();
    }
}
