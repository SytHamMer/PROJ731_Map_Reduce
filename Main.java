import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("feur");

        String file = "a";
        int nbMap= 3;
        int nbReduce=3;
        //algo


        TextDivisor textDivisor = new TextDivisor(file,nbMap);
        HashAlgo algo = HashAlgo.getInstance(nbReduce);



        //ZONE DE TEST DES ALGOS


        ArrayList<String> motsAleatoires = new ArrayList<>(Arrays.asList(
                "pomme", "chien", "ordinateur", "soleil", "banane",
                "maison", "avion", "forêt", "arc-en-ciel", "ocean",
                "plage", "fleur", "montagne", "musique", "liberté",
                "étoile", "livre", "piano", "basket", "cafétéria",
                "vélo", "université", "bouteille", "fenêtre", "cadeau",
                "sourire", "courage", "rêve", "bonheur", "énergie",
                "honnêteté", "passion", "compassion", "calme", "respect",
                "confiance", "amour", "paix", "sagesse", "patience",
                "gentillesse", "harmonie", "espoir", "charité", "joie",
                "générosité", "force", "courage", "détermination"
        ));
        HashMap<Integer,Integer> cpt = new HashMap<>();

        for(String mot: motsAleatoires){
            int res = algo.algo_lenght(mot);
            if(cpt.containsKey(res)){
                cpt.put(res,cpt.get(res)+1);
            }else{
                cpt.put(res,1);
            }
        }
        for (HashMap.Entry<Integer, Integer> entry : cpt.entrySet()) {
            System.out.println("Valeur : " + entry.getKey() + ", Nombre d'occurrences : " + entry.getValue());
        }





    }
}
