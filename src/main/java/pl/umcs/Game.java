package pl.umcs;

import pl.umcs.entities.Entity;
import pl.umcs.entities.Player;
import pl.umcs.map.Map;
import pl.umcs.utils.Reader;

import java.util.LinkedList;
import java.util.Queue;

import static pl.umcs.utils.Output.output;
import static pl.umcs.utils.Reader.reader;

public class Game {
    public static void main(String[] args) {
        /* Setup */
        // Game
        Map map = new Map();
        Player player = new Player();

        map.placeEntity(map.getCurrentStartingX(), map.getCurrentStartingY(), player);

        // Rounds
        Queue<Entity> entityOrder = new LinkedList<>();
        entityOrder.add(player);

        int currentGameLevel = -1;
        int round = 0;

        /* Main loop */
        // TODO: Refactor output to its own class
        while (player.isAlive()) {
            if (currentGameLevel != map.getCurrentLevelNumber()) {
                if (++currentGameLevel == map.getLevelsAmount()) break;

                entityOrder.clear();

                entityOrder.add(player);
                entityOrder.addAll(map.getEntities().stream().filter(e -> !(e instanceof Player)).toList());

                map.placeEntity(map.getCurrentStartingX(), map.getCurrentStartingY(), player);
            }

            Entity currentEntity = entityOrder.poll();

            if (currentEntity instanceof Player) {
                round++;

                // Print current map state
                output.print("\033[H\033[2J");
                output.flush();

                map.print();

                player.printStatistics(output);

                output.printf("Round: %d\nMove: WASD  Inventory: I\nAction: ", round);
                output.flush();

                // Take input
                char input = Reader.nextChar();

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
                        ((Player) currentEntity).handleEquipment(map);
                        break;
                }

                entityOrder.add(currentEntity);
            } else if (currentEntity != null && currentEntity.isAlive()) {
                currentEntity.performAction(map, player);

                if (currentEntity.isAlive()) entityOrder.add(currentEntity);
            }
        }

        map.printSummary(player);

        /* Cleanup */
        reader.close();
        output.close();
    }
}
