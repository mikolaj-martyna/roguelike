package pl.umcs;

import pl.umcs.entities.Player;
import pl.umcs.map.Field;
import pl.umcs.map.Map;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Misc */
        char[][] fields = {
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'|', '.', '.', '.', '.', '|', '-', '-', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'-', '-', '#', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', '-', '-', '-', '-', '-', '-', '-'},
                {' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', '.', '.', '.', '.', '.', '|'},
                {' ', ' ', '#', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#', '.', '.', '.', '.', '.', '|'},
                {' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', '|', '.', '.', '.', '.', '.', '|'},
                {' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', ' ', '-', '-', '-', '-', '-', '-', '-'}
        };

        /* Setup */
        // Input setup
        Scanner reader = new Scanner(System.in);

        // Game setup
        Map map =
                Map.builder()
                        .level(new Field[11][18])
                        .rows(11)
                        .cols(18)
                        .entities(new ArrayList<>())
                        .items(new ArrayList<>())
                        .build();

        Player player = Player.builder().build();

        map.load(fields);
        map.placeEntity(5, 3, player);

        /* Main loop */
        while (player.isAlive()) {
            // Take input
            char input = reader.next().charAt(0);

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
                    // Open inventory
                    break;
                case 'u':
                    // Open equipment
                    break;
            }

            map.print();
        }

        /* Cleanup */
        reader.close();
    }
}
