package pl.umcs;

public class Main {
    public static void main(String[] args) {
        Map map = Map.builder().level(new Field[19][11]).build();
        map.load("""
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