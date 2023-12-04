import java.util.HashMap;
import java.util.ArrayList;
public class Repartitor {
    private ArrayList<ArrayList<HashMap>> mapResult;
    private int nbReducer;

    public Repartitor(ArrayList<ArrayList<HashMap>> mapResult, int nbReducer) {
        this.mapResult = mapResult;
        this.nbReducer = nbReducer;
    }
}
