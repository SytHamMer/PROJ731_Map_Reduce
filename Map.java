import java.util.HashMap;
import java.util.ArrayList;
public class Map {
    private int id;
    private ArrayList<String> text;

    private HashAlgo algo;

    private String type_algo;

    public Map(int id, ArrayList<String> text, HashAlgo algo, String type_algo) {
        this.id = id;
        this.text = text;
        this.algo = algo;
        this.type_algo = type_algo;
    }


    public ArrayList<HashMap> map(){
        ArrayList<HashMap> res = new ArrayList<>();
        int nbRed = this.algo.getNbRed();

        for(int i =0;i<nbRed;i++) {
            res.add(i, new HashMap<>());
        }

        for(String word : text){
            int idRed = this.algo.get_algo(this.type_algo,word);
            while(word != )
            }


        }
        return res;
    }
}
