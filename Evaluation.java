import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;

public class Evaluation {
    private int nbTest;

    private ProcessCreation test;
    public Evaluation(int nbTest, ProcessCreation test) {
        this.nbTest = nbTest;
        this.test = test;
    }


    //time in ms
    public long evaluateTime(int nbMap, int nbReducer){
        long startTime = System.nanoTime();

        this.test.run(nbMap,nbReducer);

        long endTime = System.nanoTime();
        //long duration = (endTime-startTime)/1000000;
        long duration = (endTime-startTime);
        return duration;


    }

    public void bestChoice(){

        HashMap<String,Double> res = new HashMap<>();
        //For every combination possible
        for (int i =1; i<=this.test.getNbMap();i++){
            for (int j = 1;j<= this.test.getNbReduc();j++){
                ArrayList<Long> durations = new ArrayList<>();
                for(int k = 0; k< nbTest;k++){
                    durations.add(evaluateTime(i,j));
                }
                //means determination
                long sum =  0;
                for(long val : durations){
                    sum+=val;
                }
                double mean = sum/(durations.size());
                res.put(i +" Maps and " + j + " reducers",mean);
                System.out.println(res);

            }
        }
        //doing a graph is awful so just get the best combo for now (maybe save as json and make graph with python
        String bestChoice = null;
        double max = Double.MAX_VALUE;
        for (String key: res.keySet()){
            if(res.get(key)< max){
                max = res.get(key);
                bestChoice = key;
            }
        }
        System.out.println("Best choice is " + bestChoice + " with a time of " + max + "ms");
    }
}
