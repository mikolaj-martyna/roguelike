package pl.umcs;

import pl.umcs.map.Field;
import pl.umcs.map.Map;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Misc */
        char[][] fields = {
            {
                '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '|', '.', '.', '.', '.', '|', '-', '-', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ',
                ' '
            },
            {
                '-', '-', '#', '-', '-', '-', ' ', ' ', ' ', ' ', ' ', '-', '-', '-', '-', '-', '-',
                '-'
            },
            {
                ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', '.', '.', '.', '.', '.',
                '|'
            },
            {
                ' ', ' ', '#', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#', '.', '.', '.', '.', '.',
                '|'
            },
            {
                ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', '|', '.', '.', '.', '.', '.',
                '|'
            },
            {
                ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', ' ', '-', '-', '-', '-', '-', '-',
                '-'
            }
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

        Entity player =
                Entity.builder()
                        .health(new Property(20, 20, 1.0))
                        .attack(new Property(10, 10, 1.0))
                        .agility(new Property(10, 10, 1.0))
                        .defense(new Property(10, 10, 1.0))
                        .intelligence(new Property(10, 10, 1.0))
                        .charisma(new Property(10, 10, 1.0))
                        .build();

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

            switch (input) {
                case 'w':
                    // Move up
                    break;
                case 'a':
                    // Move left
                    break;
                case 's':
                    // Move down
                    break;
                case 'd':
                    // Move right
                    break;
                case 'i':
                    // Open inventory
                    break;
                case 'u':
                    // Open equipment
                    break;
            }

            // Print current game state
            map.print();
        }

        /* Cleanup */
    }
}
