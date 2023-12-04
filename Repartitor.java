import java.util.HashMap;
import java.util.ArrayList;
public class Repartitor {
    private ArrayList<ArrayList<HashMap>> mapResult;
    private int nbReducer;

    public Repartitor(ArrayList<ArrayList<HashMap>> mapResult, int nbReducer) {
        this.mapResult = mapResult;
        this.nbReducer = nbReducer;
    }


    // NEED TO BE TEST !
    public HashMap<Integer,ArrayList<HashMap>> repartitor(){
        //res Dict with id of reducer in key and list of dict of it in value

        HashMap<Integer,ArrayList<HashMap>> res = new HashMap<>();
        //initialisation of this dic
        for(int i =0;i<nbReducer;i++) {
            res.put(i, new ArrayList<>());

        }
        for(int j=0; j<mapResult.size();j++){

            res.put(j,mapResult.get(j));

        }

        return res;
    }
}
