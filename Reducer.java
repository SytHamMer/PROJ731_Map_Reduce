import java.util.HashMap;
import java.util.ArrayList;
public class Reducer {
    private int id;
    private ArrayList<HashMap<String,Integer>>  listMap;

    public Reducer(int id, ArrayList<HashMap<String, Integer>> listMap) {
        this.id = id;
        this.listMap = listMap;
    }

    public HashMap<String,Integer> reduce (){
        HashMap<String,Integer> res = new HashMap<>();
        for (HashMap<String,Integer> map : listMap){
            for(String word : map.keySet()){
                if (res.containsKey(word)){
                    res.put(word,res.get(word)+map.get(word));
                }
                else {
                    res.put(word, map.get(word));
                }
            }
        }
        return res;
    }

}
