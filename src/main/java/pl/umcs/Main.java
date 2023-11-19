package pl.umcs;

import pl.umcs.map.Field;
import pl.umcs.map.Map;

public class Main {
    public static void main(String[] args) {
        Map map = Map.builder().level(new Field[19][11]).build();
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
}
