package pl.umcs;

import pl.umcs.map.Field;
import pl.umcs.map.Map;

public class Main {
    public static void main(String[] args) {
        Map map = Map.builder().level(new Field[11][19]).build();
        map.load(
                """
                -----------      \s
                |.........|      \s
                |.........|      \s
                |.........|      \s
                |.........|      \s
                |....|-----      \s
                --#---     -------
                  #        |.....|
                  ###   ####.....|
                    #   #  |.....|
                    #####  -------\s""");
    }

    // Setup

    // Main loop

    // Cleanup
}
