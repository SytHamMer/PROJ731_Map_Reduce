
import java.util.HashMap;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {

        long starTime = System.nanoTime();

        String file = "sources/message.txt";
        int nbMap= 10;
        int nbReduce=3;
        //algo

        //initialise object
        TextDivisor textDivisor = new TextDivisor(file,nbMap);
        ArrayList<ArrayList<String>> text_divised = textDivisor.textDivision();
        HashAlgo algo = HashAlgo.getInstance(nbReduce);

        // Choose the algo
        String algo_name = "lenght";


        //create Maps DO IT IN THREAD LATTER
        ArrayList<Map> list_maps = new ArrayList<>();
        ArrayList<ArrayList<HashMap<String,Integer>>> before_reducing = new ArrayList<>();
        for (int i = 0;i<nbMap;i++){
            list_maps.add(new Map(i,text_divised.get(i),algo,algo_name));
            before_reducing.add(list_maps.get(i).map());

        }

        //Shuffle
        Repartitor repartitor = new Repartitor(before_reducing,nbReduce);
        HashMap<Integer,ArrayList<HashMap<String,Integer>>> after_repartitor = repartitor.repartitor();


        //create Reducers DO IT IN THREAD LATTER

        ArrayList<Reducer> list_reducers = new ArrayList<>();
        ArrayList<HashMap<String,Integer>> before_final_union = new ArrayList<>();
        for(int i = 0;i<nbReduce;i++){
            list_reducers.add(new Reducer(i,after_repartitor.get(i)));
            before_final_union.add(list_reducers.get(i).reduce());
        }

        //System.out.println(before_final_union.get(0));

        HashMap<String,Integer> final_one = new HashMap<>();
        for(HashMap<String,Integer> map : before_final_union){
            for (String word : map.keySet()){
                if (final_one.containsKey(word)){
                    final_one.put(word,final_one.get(word)+map.get(word));
                }else{
                    final_one.put(word,map.get(word));
                }
            }
        }

        long endTime = System.nanoTime();
        //duration in ms
        long duration = (endTime-starTime)/ 1000000;

        //System.out.println(text_divised);
        System.out.println(final_one);
        System.out.println("Durée de l'éxécution: " +  duration + " ms");


        //TEST FOR EVALUATION

        ProcessCreation test = new ProcessCreation(2,3,"sources/message.txt");
        Evaluation eval = new Evaluation(20,test);
        eval.bestChoice();

    }
}

