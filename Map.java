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

    @Override
    public String toString() {
        return "Map{" +
                "id=" + id +
                ", text=" + text.size() +
                ", algo=" + algo +
                ", type_algo='" + type_algo + '\'' +
                '}';
    }

    public ArrayList<HashMap<String,Integer>> map(){
        ArrayList<HashMap<String,Integer>> res = new ArrayList<>();
        for(int i = 0;i<algo.getNbRed();i++){
            res.add(new HashMap<String,Integer>());
        }
        HashMap<String,Integer> temporary_dict = new HashMap<>();
        for (String word : text){
            if (temporary_dict.containsKey(word)){
                temporary_dict.put(word,temporary_dict.get(word)+1);
            }
            else{
                temporary_dict.put(word,1);
            }
        }
        for (String key: temporary_dict.keySet()){
            res.get(algo.get_algo(type_algo,key)).put(key,temporary_dict.get(key));
        }
        return res;

    }
}
