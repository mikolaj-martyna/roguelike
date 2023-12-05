package pl.umcs;

import pl.umcs.map.Field;
import pl.umcs.map.Map;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map map = Map.builder().level(new Field[11][18]).rows(11).cols(18).build();
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
        map.load(fields);

        // Setup
        Scanner myObj = new Scanner(System.in);

        Entity player =
                Entity.builder()
                        .x(1)
                        .y(1)
                        .health(new Property(20, 20, 1.0))
                        .attack(new Property(10, 10, 1.0))
                        .agility(new Property(10, 10, 1.0))
                        .defense(new Property(10, 10, 1.0))
                        .intelligence(new Property(10, 10, 1.0))
                        .charisma(new Property(10, 10, 1.0))
                        .build();

        // Main loop
        while (player.isAlive()) {
            // Take input
            char input = myObj.next().charAt(0);

            // Parse input
            // Print current game state
            map.print();
            //            System.out.println("dhjnjsn'\n");
        }

        // Cleanup
    }
}
