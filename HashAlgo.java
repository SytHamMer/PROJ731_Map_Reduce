import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
public class HashAlgo {
    private static HashAlgo instance;
    private int nbRed;

    private HashAlgo(int nbRed) {
        this.nbRed = nbRed;
    }

    public static HashAlgo getInstance(int nbRed) {
        if (instance == null) {
            instance = new HashAlgo(nbRed);
        }
        return instance;
    }

    public int getNbRed() {
        return this.nbRed;
    }

    //OPTI ?
    public int get_algo(String type_algo,String word){
        switch(type_algo){
            case "random":
                return algo_random(word);

            case "lenght":
            return algo_lenght(word);
        }
        return 0;
    }

    public int algo_random(String word){
        Random random = new Random();
        int randInt = random.nextInt(nbRed);
        return(randInt);
    }

    public int algo_lenght(String word){
        byte[] bytes = word.getBytes();
        int sum = 0;
        for (byte b : bytes) {
            sum+=  b & 0xFF;
        }

        return (sum%=nbRed);
    }

}
