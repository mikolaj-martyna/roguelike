package pl.umcs;

import java.util.Random;

public class Dice {
    public static int rollDice(int amount, int maxRange) {
        int result = 0;

        for (int i = 0; i < amount; i++) {
            result += new Random().nextInt(maxRange) + 1;
        }

        return result;
    }
}
