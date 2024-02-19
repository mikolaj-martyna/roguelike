package pl.umcs.utils;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Reader {
    public static Scanner reader = new Scanner(System.in, StandardCharsets.UTF_8);

    public static char nextChar() {
        return reader.next().charAt(0);
    }
}
