import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProcessCreation {
    private int nbReduc;
    private int nbMap;
    private String filename;

    public ProcessCreation(String filename) {
        this.filename = filename;
    }
    // GETTERS AND SETTERS
    public int getNbReduc() {
        return nbReduc;
    }

    public void setNbReduc(int nbReduc) {
        this.nbReduc = nbReduc;
    }

    public int getNbMap() {
        return nbMap;
    }

    public void setNbMap(int nbMap) {
        this.nbMap = nbMap;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    // CREATE THE PROCESS WITH MULTIPROCESSING HERE
    //WE NEED TO KEEP THIS TO INPUT

    public HashMap<String,Integer> run(int nbMap, int nbReduc){
        //HERE MAKE EVERYTHING (CHANGE THE OUTPUT)
        //initialise object
        TextDivisor textDivisor = new TextDivisor(this.filename,nbMap);
        ArrayList<ArrayList<String>> text_divised = textDivisor.textDivision();
        HashAlgo algo = HashAlgo.getInstance(nbReduc);
        String algo_name = "lenght";

        //MAP multithreading

        //Creation of map object

        ArrayList<Map> list_maps = new ArrayList<>();
        for (int i = 0; i < nbMap; i++) {
            list_maps.add(new Map(i, text_divised.get(i), algo, algo_name));
        }

        //List results
        ArrayList<ArrayList<HashMap<String,Integer>>> before_reducing = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(nbMap);
        //creation and execution of multithreading
        for (int i = 0; i < nbMap; i++) {
            int mapIndex = i;
            executor.submit(() -> {
                ArrayList<HashMap<String, Integer>> result = list_maps.get(mapIndex).map();
                before_reducing.add(result);
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Erreur d'interruption des threads : " + e.getMessage());
        }


        //Shuffle
        Repartitor repartitor = new Repartitor(before_reducing,nbReduc);
        HashMap<Integer,ArrayList<HashMap<String,Integer>>> after_repartitor = repartitor.repartitor();



        // Same technic for reducers

        //Create Reducer
        ArrayList<Reducer> list_reducers = new ArrayList<>();
        for (int i = 0; i < nbReduc; i++) {
            list_reducers.add(new Reducer(i, after_repartitor.get(i)));
        }

        ArrayList<HashMap<String,Integer>> before_final_union = new ArrayList<>();

        //creation and execution of multithreading
        ExecutorService executorReducers = Executors.newFixedThreadPool(nbReduc);

        for (int i = 0; i < nbReduc; i++) {
            int reducerIndex = i;
            executorReducers.submit(() -> {
                HashMap<String, Integer> result = list_reducers.get(reducerIndex).reduce();
                before_final_union.add(result);
            });
        }


        executorReducers.shutdown();
        try {
            executorReducers.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Erreur d'interruption des threads : " + e.getMessage());
        }

        //Reunited all reducers


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


        return final_one ;
    }
}
