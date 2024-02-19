package pl.umcs.utils;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Output {
    public static PrintWriter output = new PrintWriter(System.out, false, StandardCharsets.UTF_8);
}
