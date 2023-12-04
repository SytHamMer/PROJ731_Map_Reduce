import java.util.HashMap;
import java.util.ArrayList;
public class Map {
    private int id;
    private String text;

    private HashAlgo algo;

    public Map(int id, String text, HashAlgo algo) {
        this.id = id;
        this.text = text;
        this.algo = algo;
    }
}
